package com.remotely.io.presentation.authentication.forgotpassword

sealed interface ForgotPasswordUIEvents {
    data class OnEmailChanged(val email: String) : ForgotPasswordUIEvents
    data class OnForgotPassword(
        val emptyEmailError: String,
        val emailError: String
    ) : ForgotPasswordUIEvents
}