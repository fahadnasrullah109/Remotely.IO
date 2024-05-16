package com.remotely.io.domain.usecases

import com.remotely.io.domain.repo.IRepository
import kotlinx.coroutines.CoroutineDispatcher

class MarkIntroductionShownUseCase(
    private val repository: IRepository, private val dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, Unit>(dispatcher) {
    public override fun execute(parameters: Unit) = repository.markIntroductionShown()
}