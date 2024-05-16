package com.remotely.io.domain.usecases

import com.remotely.io.data.DataResource
import com.remotely.io.domain.repo.IRepository
import kotlinx.coroutines.CoroutineDispatcher

class LogoutUseCase(
    private val repository: IRepository, private val dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, DataResource<Boolean>>(dispatcher) {
    public override fun execute(parameters: Unit) = repository.logout()
}