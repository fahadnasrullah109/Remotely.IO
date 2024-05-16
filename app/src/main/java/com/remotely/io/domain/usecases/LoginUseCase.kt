package com.remotely.io.domain.usecases

import com.remotely.io.data.DataResource
import com.remotely.io.data.models.response.LoginResponse
import com.remotely.io.domain.repo.IRepository
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Response

class LoginUseCase(
    private val repository: IRepository, private val dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, DataResource<Response<LoginResponse>>>(dispatcher) {
    public override fun execute(parameters: Unit) = repository.login()
}