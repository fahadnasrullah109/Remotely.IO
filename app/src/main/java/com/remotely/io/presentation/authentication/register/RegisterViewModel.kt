package com.remotely.io.presentation.authentication.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.remotely.io.domain.models.ValidationResult
import com.remotely.io.domain.usecases.EmailValidationUseCase
import com.remotely.io.domain.usecases.PasswordValidationUseCase
import com.remotely.io.domain.usecases.PhoneValidationUseCase
import com.remotely.io.domain.usecases.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val emailValidationUseCase: EmailValidationUseCase,
    private val passwordValidationUseCase: PasswordValidationUseCase,
    private val phoneValidationUseCase: PhoneValidationUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: RegisterUIEvents) {
        viewModelScope.launch {
            when (event) {
                is RegisterUIEvents.OnRegister -> {

                    val emailResult = emailValidationUseCase.execute(
                        field = _uiState.value.email,
                        emptyErrorString = event.emptyEmailError,
                        errorString = event.emailError
                    )

                    val phoneResult = phoneValidationUseCase.execute(
                        field = _uiState.value.phone,
                        emptyErrorString = event.emptyPhoneError,
                        errorString = event.phoneError
                    )

                    val passwordResult = passwordValidationUseCase.execute(
                        field = _uiState.value.password,
                        emptyErrorString = event.emptyPasswordError,
                        errorString = event.passwordError
                    )

                    val hasError = listOf(emailResult, phoneResult, passwordResult).any {
                        it is ValidationResult.Error
                    }
                    if (hasError) {
                        _uiState.value = _uiState.value.copy(
                            emailError = when (emailResult) {
                                is ValidationResult.Success -> null
                                is ValidationResult.Error -> emailResult.errorMessage
                            }, phoneError = when (phoneResult) {
                                is ValidationResult.Success -> null
                                is ValidationResult.Error -> phoneResult.errorMessage
                            }, passwordError = when (passwordResult) {
                                is ValidationResult.Success -> null
                                is ValidationResult.Error -> passwordResult.errorMessage
                            }
                        )
                    } else {
                        _uiState.value = _uiState.value.copy(
                            loading = true, emailError = null, phoneError = null,
                            passwordError = null
                        )
                        delay(3000)
                        _uiState.value = _uiState.value.copy(
                            loading = false, registerSuccess = true
                        )
                    }
                }

                is RegisterUIEvents.OnPhoneChanged -> _uiState.value =
                    _uiState.value.copy(phone = event.phone)

                is RegisterUIEvents.OnEmailChanged -> _uiState.value =
                    _uiState.value.copy(email = event.email)

                is RegisterUIEvents.OnPasswordChanged -> _uiState.value =
                    _uiState.value.copy(password = event.password)
            }
        }
    }

    private fun resetUIStates() {
        _uiState.value = _uiState.value.copy(
            emailError = null, passwordError = null, error = null
        )
    }
}