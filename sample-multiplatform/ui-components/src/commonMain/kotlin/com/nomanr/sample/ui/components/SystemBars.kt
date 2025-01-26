package com.nomanr.sample.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.nomanr.sample.ui.AppTheme

@Composable
fun SystemBars(colors: SystemBarColor) {}

@Immutable
data class SystemBarColor(
    val statusBarColor: Color,
    val navigationBarColor: Color
)

object SystemBarsDefaults {
    @Composable
    fun defaultColors() = SystemBarColor(
        statusBarColor = AppTheme.colors.primary,
        navigationBarColor = AppTheme.colors.background
    )

    @Composable
    fun colors(isStatusContentBarDark: Boolean, isNavigationContentBarDark: Boolean): SystemBarColor {
        val statusBarColor = if (isStatusContentBarDark) AppTheme.colors.background else AppTheme.colors.primary
        val navigationBarColor = if (isNavigationContentBarDark) AppTheme.colors.background else AppTheme.colors.primary
        return SystemBarColor(statusBarColor, navigationBarColor)
    }
}
