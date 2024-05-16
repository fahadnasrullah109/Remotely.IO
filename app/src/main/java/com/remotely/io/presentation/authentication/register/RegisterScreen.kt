package com.remotely.io.presentation.authentication.register

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.remotely.io.theme.RemotelyIOTheme

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel,
    onRegisterSuccess: () -> Unit,
    onLoginTap: () -> Unit,
    onTermsTap: () -> Unit
) {

}

@Preview
@Composable
private fun RegisterScreenPreview() {
    RemotelyIOTheme {
        RegisterScreen(modifier = Modifier.fillMaxSize(),
            viewModel = hiltViewModel(),
            onRegisterSuccess = {},
            onLoginTap = {},
            onTermsTap = {})
    }
}