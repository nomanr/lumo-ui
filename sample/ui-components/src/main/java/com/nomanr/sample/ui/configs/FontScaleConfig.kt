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
import com.nomanr.sample.ui.AppColors
import com.nomanr.sample.ui.Colors
import com.nomanr.sample.ui.LocalDefaultColors

@Composable
fun rememberAppConfigState(): AppConfigState {
    val configurations = LocalConfiguration.current
    val layoutDirection = LocalLayoutDirection.current
    val colors = LocalDefaultColors.current

    return remember {
        AppConfigState(
            systemFontScale = configurations.fontScale, systemLayoutDirection = layoutDirection, defaultColors = colors
        )
    }
}

class AppConfigState(val systemFontScale: Float, val systemLayoutDirection: LayoutDirection, val defaultColors: AppColors) {
    var fontScale by mutableFloatStateOf(systemFontScale)
        private set
    var layoutDirection by mutableStateOf(systemLayoutDirection)
        private set

    var colors by mutableStateOf(defaultColors)
        private set


    fun updateFontScale(newScale: Float) {
        fontScale = newScale
    }

    fun updateLayoutDirection(newDirection: LayoutDirection) {
        layoutDirection = newDirection
    }

    fun updateColors(newColors: AppColors) {
        colors = newColors
    }

    fun resetFontScale() {
        fontScale = systemFontScale
    }

    fun resetColors() {
        colors = defaultColors
    }
}

val LocalAppConfigState = staticCompositionLocalOf<AppConfigState> {
    error("No AppConfigState provided")
}