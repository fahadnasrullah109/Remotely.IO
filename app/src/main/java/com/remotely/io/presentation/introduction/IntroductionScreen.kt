package com.remotely.io.presentation.introduction

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.remotely.io.theme.RemotelyIOTheme

@Composable
fun IntroductionScreen(
    modifier: Modifier = Modifier,
    viewModel: IntroductionViewModel,
    onGetStarted: () -> Unit
) {

}

@Preview
@Composable
private fun IntroductionScreenPreview() {
    RemotelyIOTheme {
        IntroductionScreen(viewModel = hiltViewModel()) {}
    }
}