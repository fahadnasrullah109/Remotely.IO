package com.remotely.io.domain.usecases

import com.remotely.io.data.DataResource
import com.remotely.io.domain.models.DomainUser
import com.remotely.io.domain.repo.IRepository
import kotlinx.coroutines.CoroutineDispatcher

class GetLoggedInUserUseCase(
    private val repository: IRepository, private val dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, DataResource<DomainUser?>>(dispatcher) {
    public override fun execute(parameters: Unit) = repository.getLoggedInUser()
}