package com.remotely.io.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.remotely.io.navigation.RemotelyIONavHost
import com.remotely.io.theme.RemotelyIOTheme
import com.remotely.io.theme.appBgColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            RemotelyIOTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = appBgColor
                ) {
                    RemotelyIONavHost(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}