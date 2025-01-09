package com.nomanr.lumo.ui.components

import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.ColorUtils
import com.nomanr.lumo.ui.AppTheme

@Composable
fun SystemBars(colors: SystemBarColor) {
    val context = LocalContext.current as? ComponentActivity ?: return

    val isStatusBarContentLight = shouldUseLightSystemBarIcons(colors.statusBarColor)
    val isNavigationBarContentLight = shouldUseLightSystemBarIcons(colors.navigationBarColor)


    // Since we are applying the status bar with edge-to-edge configuration,
    // the background and top bar colors will extend and cover the system bars.
    // The system bars themselves will be transparent, and we will only change
    // the content colors based on the luminance of the background colors.
    // Note: if you'd like to change the system bar colors, you can apply the passed color
    // instead of transparent.
    val transparent = Color.Transparent.toArgb()

    LaunchedEffect(isStatusBarContentLight, isNavigationBarContentLight) {
        context.enableEdgeToEdge(
            statusBarStyle = if (isStatusBarContentLight) {
                SystemBarStyle.dark(
                    transparent
                )
            } else {
                SystemBarStyle.light(
                    transparent,
                    transparent
                )
            },
            navigationBarStyle = if (isNavigationBarContentLight) {
                SystemBarStyle.dark(
                    transparent
                )
            } else {
                SystemBarStyle.light(
                    transparent,
                    transparent
                )
            }
        )
    }
}

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

fun shouldUseLightSystemBarIcons(color: Color): Boolean {
    val androidColor = color.toArgb()
    val luminance = ColorUtils.calculateLuminance(androidColor)
    return luminance < 0.5
}
