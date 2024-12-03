package com.nomanr.sample.ui.config

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun rememberAppConfigsState(): AppConfigsState {
    return remember { AppConfigsState() }
}


class AppConfigsState {
    private var fontScale by mutableFloatStateOf(1f)

    fun updateFontScale(newScale: Float) {
        fontScale = newScale
    }
}