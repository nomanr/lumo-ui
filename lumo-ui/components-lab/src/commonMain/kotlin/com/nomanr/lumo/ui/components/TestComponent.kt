package com.nomanr.lumo.ui.components

import androidx.compose.runtime.Composable

@Composable
fun PlatformGreeting() {
    Text(text = getPlatformGreeting())
}

expect fun getPlatformGreeting(): String