package com.remotely.io.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.remotely.io.data.local.AppDatabase.Companion.DATABASE_VERSION
import com.remotely.io.data.models.db.User

@Database(
    entities = [User::class],
    version = DATABASE_VERSION,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {

        private const val DB_NAME = "Remotely-IO.db"
        const val DATABASE_VERSION = 1

        // to void duplicate instances of DB
        @Volatile
        private var instance: AppDatabase? = null
        fun init(context: Context, useInMemoryDb: Boolean = false): AppDatabase {
            if (instance != null && !useInMemoryDb) {
                return instance!!
            }
            synchronized(this) {
                instance = if (useInMemoryDb) {
                    Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                } else {
                    Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                }.fallbackToDestructiveMigration().build()
                return instance!!
            }
        }
    }
}