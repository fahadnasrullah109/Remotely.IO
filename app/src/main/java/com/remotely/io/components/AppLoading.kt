package com.remotely.io.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.remotely.io.theme.RemotelyIOTheme

@Composable
fun Loading(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            CircularProgressIndicator()
    }
}


@Preview
@Composable
private fun LoadingPreview() {
    RemotelyIOTheme {
        Loading(modifier = Modifier.fillMaxSize())
    }
}