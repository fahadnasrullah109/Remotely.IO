package com.remotely.io.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
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
    SplashContent(modifier = modifier)
    LaunchedEffect(key1 = uiState) {
        if (uiState.route.isNotEmpty()) {
            onTimeout(uiState.route)
        }
    }
}

@Composable
private fun SplashContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Image(
            contentScale = ContentScale.Crop,
            modifier = modifier.fillMaxSize(),
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