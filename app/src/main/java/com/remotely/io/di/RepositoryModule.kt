package com.remotely.io.di

import android.content.Context
import com.remotely.io.data.local.UserDao
import com.remotely.io.data.remote.NetworkApiService
import com.remotely.io.data.repository.RepositoryImpl
import com.remotely.io.domain.repo.IRepository
import com.remotely.io.preferences.DatastorePreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideRepository(
        @ApplicationContext context: Context,
        apiService: NetworkApiService,
        userDao: UserDao,
        preferences: DatastorePreferences
    ): IRepository {
        return RepositoryImpl(
            context,
            apiService,
            userDao,
            preferences,
            Dispatchers.IO
        )
    }

}