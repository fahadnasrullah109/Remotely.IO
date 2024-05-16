package com.remotely.io.presentation.introduction

sealed interface IntroductionUIEvents {
    data object OnIntroductionShown : IntroductionUIEvents
}