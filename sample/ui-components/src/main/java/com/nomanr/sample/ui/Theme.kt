package com.nomanr.sample.ui

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.nomanr.sample.ui.foundation.ripple

object AppTheme {
    val colors: Colors
        @ReadOnlyComposable @Composable get() = LocalColors.current

    val typography: Typography
        @ReadOnlyComposable @Composable get() = LocalTypography.current
}

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val rippleIndication = ripple()
    val selectionColors = rememberTextSelectionColors(LightColors)
    val colors = if (isDarkTheme) DarkColors else LightColors

    CompositionLocalProvider(
        LocalColors provides colors,
        LocalTypography provides typography,
        LocalIndication provides rippleIndication,
        LocalTextSelectionColors provides selectionColors,
        content = content
    )
}

@Composable
fun contentColorFor(color: Color): Color {
    return AppTheme.colors.contentColorFor(color)
}

@Composable
internal fun rememberTextSelectionColors(colorScheme: Colors): TextSelectionColors {
    val primaryColor = colorScheme.primary
    return remember(primaryColor) {
        TextSelectionColors(
            handleColor = primaryColor,
            backgroundColor = primaryColor.copy(alpha = TextSelectionBackgroundOpacity),
        )
    }
}

internal const val TextSelectionBackgroundOpacity = 0.4f
