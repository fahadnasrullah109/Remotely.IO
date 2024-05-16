package com.remotely.io.di

import com.remotely.io.domain.repo.IRepository
import com.remotely.io.domain.usecases.EmailValidationUseCase
import com.remotely.io.domain.usecases.GetLoggedInUserUseCase
import com.remotely.io.domain.usecases.LoginUseCase
import com.remotely.io.domain.usecases.LogoutUseCase
import com.remotely.io.domain.usecases.MarkIntroductionShownUseCase
import com.remotely.io.domain.usecases.PasswordValidationUseCase
import com.remotely.io.domain.usecases.RegisterUseCase
import com.remotely.io.domain.usecases.ShowIntroductionDecisionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @ViewModelScoped
    @Provides
    fun providePasswordValidationUseCase() = PasswordValidationUseCase()

    @ViewModelScoped
    @Provides
    fun provideEmailValidationUseCase() = EmailValidationUseCase()

    @ViewModelScoped
    @Provides
    fun provideShowIntroductionDecisionUseCase(repository: IRepository) =
        ShowIntroductionDecisionUseCase(repository, Dispatchers.IO)

    @ViewModelScoped
    @Provides
    fun provideGetLoggedInUserUseCase(repository: IRepository) =
        GetLoggedInUserUseCase(repository, Dispatchers.IO)

    @ViewModelScoped
    @Provides
    fun provideLoginUseCase(repository: IRepository) = LoginUseCase(repository, Dispatchers.IO)


    @ViewModelScoped
    @Provides
    fun provideMarkIntroductionShownUseCase(repository: IRepository) =
        MarkIntroductionShownUseCase(repository, Dispatchers.IO)

    @ViewModelScoped
    @Provides
    fun provideRegisterUseCase(repository: IRepository) =
        RegisterUseCase(repository, Dispatchers.IO)

    @ViewModelScoped
    @Provides
    fun provideLogoutUseCase(repository: IRepository) = LogoutUseCase(repository, Dispatchers.IO)
}