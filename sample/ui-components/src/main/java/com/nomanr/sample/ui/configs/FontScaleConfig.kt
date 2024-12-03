package com.nomanr.sample.ui.configs

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun rememberFontScaleState(): FontScaleState {
    val configurations = LocalConfiguration.current

    return remember {
        FontScaleState(configurations.fontScale)
    }
}

class FontScaleState(val systemFontScale: Float) {
    var fontScale by mutableFloatStateOf(systemFontScale)
        private set

    fun updateFontScale(newScale: Float) {
        fontScale = newScale
    }

    fun resetFontScale() {
        fontScale = systemFontScale
    }

}

val LocalFontScaleState = staticCompositionLocalOf<FontScaleState> {
    error("No FontScaleState provided")
}