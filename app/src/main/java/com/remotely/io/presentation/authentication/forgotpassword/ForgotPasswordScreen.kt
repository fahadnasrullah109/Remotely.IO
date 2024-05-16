package com.remotely.io.presentation.authentication.forgotpassword

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.remotely.io.theme.RemotelyIOTheme

@Composable
fun ForgotPasswordScreen(
    modifier: Modifier = Modifier, viewModel: ForgotPasswordViewModel, onEmailSent: () -> Unit,
) {
}

@Preview
@Composable
private fun ForgotPasswordScreenPreview() {
    RemotelyIOTheme {
        ForgotPasswordScreen(
            modifier = Modifier.fillMaxSize(),
            viewModel = hiltViewModel(),
            onEmailSent = {},
        )
    }
}