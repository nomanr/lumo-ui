package com.nomanr.sample.ui.configs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection

@Composable
fun rememberAppConfigState(): AppConfigState {
    val configurations = LocalConfiguration.current
    val layoutDirection = LocalLayoutDirection.current

    return remember {
        AppConfigState(
            systemFontScale = configurations.fontScale, systemLayoutDirection = layoutDirection
        )
    }
}

class AppConfigState(val systemFontScale: Float, val systemLayoutDirection: LayoutDirection) {
    var fontScale by mutableFloatStateOf(systemFontScale)
        private set
    var layoutDirection by mutableStateOf(systemLayoutDirection)
        private set


    fun updateFontScale(newScale: Float) {
        fontScale = newScale
    }

    fun updateLayoutDirection(newDirection: LayoutDirection) {
        layoutDirection = newDirection
    }

    fun resetFontScale() {
        fontScale = systemFontScale
    }
}

val LocalAppConfigState = staticCompositionLocalOf<AppConfigState> {
    error("No AppConfigState provided")
}