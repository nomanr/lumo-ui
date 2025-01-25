package com.nomanr.sample.ui.components

import androidx.compose.runtime.Composable

@Composable
fun PlatformGreeting() {
    Text(text = getPlatformGreeting())
}

expect fun getPlatformGreeting(): String