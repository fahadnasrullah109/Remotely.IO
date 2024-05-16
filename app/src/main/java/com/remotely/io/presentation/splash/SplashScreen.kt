package com.remotely.io.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.remotely.io.R
import com.remotely.io.theme.RemotelyIOTheme

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onTimeout: (String) -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    SplashContent(modifier = modifier.background(color = MaterialTheme.colorScheme.primary))
    LaunchedEffect(key1 = uiState) {
        if (uiState.route.isNotEmpty()) {
            onTimeout(uiState.route)
        }
    }
}

@Composable
private fun SplashContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize().padding(100.dp), contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.ic_remotely_io),
            contentDescription = "Splash"
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    RemotelyIOTheme {
        SplashScreen(onTimeout = {})
    }
}