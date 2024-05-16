package com.remotely.io.data.repository

import android.content.Context
import com.remotely.io.data.DataResource
import com.remotely.io.data.local.UserDao
import com.remotely.io.data.mappers.UserMapper
import com.remotely.io.data.models.db.User
import com.remotely.io.data.models.response.LoginResponse
import com.remotely.io.data.remote.NetworkApiService
import com.remotely.io.domain.models.DomainUser
import com.remotely.io.domain.repo.IRepository
import com.remotely.io.preferences.DatastorePreferences
import com.remotely.io.utils.getDummyLoginResponse
import com.remotely.io.utils.getRandomUUID
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class RepositoryImpl(
    private val context: Context,
    private val apiService: NetworkApiService,
    private val userDao: UserDao,
    private val preferences: DatastorePreferences,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : IRepository {

    private val userMapper by lazy { UserMapper() }
    override fun shouldShowIntroduction(): Flow<DataResource<Boolean>> = flow {
        val isShown = preferences.isIntroductionPresented.firstOrNull()
        isShown?.let {
            emit(DataResource.Success(it.not()))
        } ?: run {
            emit(DataResource.Success(true))
        }

    }.flowOn(dispatcher)

    override fun getLoggedInUser(): Flow<DataResource<DomainUser?>> = flow {
        emit(DataResource.Loading)
        try {
            val user = userDao.getLoggedInUser()
            user?.let {
                emit(DataResource.Success(userMapper.mapToDomain(it)))
            } ?: run {
                emit(
                    DataResource.Error<Any>(
                        exception = RuntimeException(
                            "No Logged In User found"
                        )
                    )
                )
            }
        } catch (e: Exception) {
            emit(
                DataResource.Error<Any>(
                    exception = RuntimeException(
                        e.message ?: "Not able to get Logged In User"
                    )
                )
            )
        }
    }.flowOn(dispatcher)

    override fun logout(): Flow<DataResource<Boolean>> = flow {
        emit(DataResource.Loading)
        userDao.clear()
        emit(DataResource.Success(true))

    }.flowOn(dispatcher)

    override fun login(): Flow<DataResource<Response<LoginResponse>>> = flow {
        emit(DataResource.Loading)
        val usr = getDummyLoginResponse()
        userDao.insert(
            User(
                userId = usr.ID ?: getRandomUUID(),
                name = (usr.firstName ?: "firstname").plus(" ").plus(
                    usr.lastName ?: "lastname"
                ),
                profilePicture = "https://ibb.co/T4YShT1",
                location = "Lahore, Pakistan"
            )
        )
        emit(DataResource.Success(Response.success(usr)))
    }.flowOn(dispatcher)

    override fun register(): Flow<DataResource<Response<LoginResponse>>> = flow {
        emit(DataResource.Loading)
        val usr = getDummyLoginResponse()
        emit(DataResource.Success(Response.success(usr)))
    }.flowOn(dispatcher)

    override fun forgotPassword(): Flow<DataResource<Response<LoginResponse>>> = flow {
        emit(DataResource.Loading)
        val usr = getDummyLoginResponse()
        emit(DataResource.Success(Response.success(usr)))
    }.flowOn(dispatcher)

    override fun markIntroductionShown(): Flow<Unit> = flow<Unit> {
        preferences.saveIntroductionShown()
    }.flowOn(dispatcher)

    companion object {
        private const val TAG = "Repository"
    }
}
