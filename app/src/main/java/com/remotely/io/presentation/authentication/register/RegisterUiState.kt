package com.remotely.io.presentation.authentication.register

import com.remotely.io.components.UiText

data class RegisterUiState(
    val email: String = "",
    val emailError: UiText? = null,
    val phone: String = "",
    val phoneError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null,
    val loading: Boolean = false,
    val registerSuccess: Boolean = false,
    val error: String? = null
)