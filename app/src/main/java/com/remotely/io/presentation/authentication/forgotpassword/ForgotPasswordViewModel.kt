package com.remotely.io.presentation.authentication.forgotpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.remotely.io.domain.models.ValidationResult
import com.remotely.io.domain.usecases.EmailValidationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val emailValidationUseCase: EmailValidationUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ForgotPasswordUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: ForgotPasswordUIEvents) {
        viewModelScope.launch {
            when (event) {
                is ForgotPasswordUIEvents.OnForgotPassword -> {
                    val emailResult = emailValidationUseCase.execute(
                        field = _uiState.value.email,
                        emptyErrorString = event.emptyEmailError,
                        errorString = event.emailError
                    )
                    val hasError = listOf(emailResult).any {
                        it is ValidationResult.Error
                    }
                    if (hasError) {
                        _uiState.value = _uiState.value.copy(
                            emailError = when (emailResult) {
                                is ValidationResult.Success -> null
                                is ValidationResult.Error -> emailResult.errorMessage
                            }
                        )
                    } else {
                        _uiState.value = _uiState.value.copy(
                            loading = true,
                            emailError = null,
                        )
                        delay(3000)
                        _uiState.value = _uiState.value.copy(
                            loading = false, emailSentSuccess = true
                        )
                    }
                }

                is ForgotPasswordUIEvents.OnEmailChanged -> _uiState.value =
                    _uiState.value.copy(email = event.email)

            }
        }
    }
}