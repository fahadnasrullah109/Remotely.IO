package com.remotely.io.presentation.introduction

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.remotely.io.R
import com.remotely.io.components.AppButton
import com.remotely.io.theme.RemotelyIOTheme
import com.remotely.io.theme.soraFamily
import com.remotely.io.theme.textGrayColor

@Composable
fun IntroductionScreen(
    modifier: Modifier = Modifier,
    onLogin: () -> Unit,
    viewModel: IntroductionViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(IntroductionUIEvents.OnIntroductionShown)
    }
    IntroductionScreenContent(modifier = modifier, onLogin = onLogin)
}

@Composable
private fun IntroductionScreenContent(
    modifier: Modifier = Modifier,
    onLogin: () -> Unit,
) {
    Box(modifier = modifier, contentAlignment = Alignment.BottomCenter) {
        Image(
            contentScale = ContentScale.Crop,
            modifier = modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.ic_onboarding),
            contentDescription = "Coffee Main Image"
        )
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {

            Text(
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontFamily = soraFamily,
                    fontWeight = FontWeight.SemiBold
                ),
                text = stringResource(id = R.string.label_introduction_welcome).toUpperCase(Locale.current),
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                fontSize = 30.sp,
                style = TextStyle(
                    color = Color.White, fontFamily = soraFamily, fontWeight = FontWeight.SemiBold
                ),
                text = stringResource(id = R.string.label_introduction_title),
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    color = Color.White, fontFamily = soraFamily
                ),
                text = stringResource(id = R.string.label_introduction_description),
                color = Color(0xAAFFFFFF)
            )

            Spacer(modifier = Modifier.height(20.dp))

            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                buttonTitle = stringResource(id = R.string.label_browse_shops),
                onTap = onLogin
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                TextButton(onClick = onLogin) {
                    Text(
                        fontSize = 20.sp,
                        style = TextStyle(
                            color = Color.White,
                            fontFamily = soraFamily,
                            fontWeight = FontWeight.SemiBold
                        ),
                        text = stringResource(id = R.string.label_introduction_login),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun IntroductionScreenContentPreview() {
    RemotelyIOTheme {
        IntroductionScreenContent(modifier = Modifier.fillMaxSize(), onLogin = {})
    }
}