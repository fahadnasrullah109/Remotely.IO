package com.remotely.io.presentation.authentication.register

sealed interface RegisterUIEvents {
    data class OnUsernameChanged(val username: String) : RegisterUIEvents
    data class OnEmailChanged(val email: String) : RegisterUIEvents
    data class OnPasswordChanged(val password: String) : RegisterUIEvents
    data class OnRegister(
        val emptyUsernameError: String,
        val usernameError: String,
        val emptyEmailError: String,
        val emailError: String,
        val emptyPasswordError: String,
        val passwordError: String,
    ) : RegisterUIEvents
}