package com.remotely.io.presentation.authentication.register

sealed interface RegisterUIEvents {
    data class OnEmailChanged(val email: String) : RegisterUIEvents
    data class OnPhoneChanged(val phone: String) : RegisterUIEvents
    data class OnPasswordChanged(val password: String) : RegisterUIEvents
    data class OnRegister(
        val emptyEmailError: String,
        val emailError: String,
        val emptyPhoneError: String,
        val phoneError: String,
        val emptyPasswordError: String,
        val passwordError: String,
    ) : RegisterUIEvents
}