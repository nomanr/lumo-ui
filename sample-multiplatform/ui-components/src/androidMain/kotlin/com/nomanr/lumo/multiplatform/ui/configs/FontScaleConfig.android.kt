package com.nomanr.lumo.multiplatform.ui.configs

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@Composable
actual fun getSystemFontScale(): Float {
    val configurations = LocalConfiguration.current
    return configurations.fontScale
}
