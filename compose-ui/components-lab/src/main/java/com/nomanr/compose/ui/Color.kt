package com.nomanr.compose.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Black: Color = Color(0xFF000000)
val Gray900: Color = Color(0xFF282828)
val Gray800: Color = Color(0xFF4b4b4b)
val Gray700: Color = Color(0xFF5e5e5e)
val Gray600: Color = Color(0xFF727272)
val Gray500: Color = Color(0xFF868686)
val Gray400: Color = Color(0xFFC7C7C7)
val Gray300: Color = Color(0xFFDFDFDF)
val Gray200: Color = Color(0xFFE2E2E2)
val Gray100: Color = Color(0xFFF7F7F7)
val Gray50: Color = Color(0xFFFFFFFF)
val White: Color = Color(0xFFFFFFFF)

val Red900: Color = Color(0xFF520810)
val Red800: Color = Color(0xFF950f22)
val Red700: Color = Color(0xFFbb032a)
val Red600: Color = Color(0xFFde1135)
val Red500: Color = Color(0xFFf83446)
val Red400: Color = Color(0xFFfc7f79)
val Red300: Color = Color(0xFFffb2ab)
val Red200: Color = Color(0xFFffd2cd)
val Red100: Color = Color(0xFFffe1de)
val Red50: Color = Color(0xFFfff0ee)

val Blue900: Color = Color(0xFF276EF1)
val Blue800: Color = Color(0xFF3F7EF2)
val Blue700: Color = Color(0xFF578EF4)
val Blue600: Color = Color(0xFF6F9EF5)
val Blue500: Color = Color(0xFF87AEF7)
val Blue400: Color = Color(0xFF9FBFF8)
val Blue300: Color = Color(0xFFB7CEFA)
val Blue200: Color = Color(0xFFCFDEFB)
val Blue100: Color = Color(0xFFE7EEFD)
val Blue50: Color = Color(0xFFFFFFFF)

@Immutable
data class Colors(
    val primary: Color,
    val onPrimary: Color,

    val secondary: Color,
    val onSecondary: Color,

    val tertiary: Color,
    val onTertiary: Color,

    val error: Color,
    val onError: Color,

    val disabled: Color,
    val onDisabled: Color,

    val surface: Color,
    val onSurface: Color,

    val background: Color,
    val onBackground: Color,

    val outline: Color,

    val transparent: Color = Color.Transparent,
    val white: Color = White,
    val black: Color = Black,

    val text: Color,
    val textSecondary: Color,
    val textDisabled: Color,

    val scrim: Color,
    val elevation: Color,
)

internal val LightColors = Colors(
    primary = Black,
    onPrimary = White,

    secondary = Gray400,
    onSecondary = Black,

    tertiary = Blue900,
    onTertiary = White,

    surface = Gray200,
    onSurface = Black,

    error = Red600,
    onError = White,

    disabled = Gray100,
    onDisabled = Gray500,

    background = White,
    onBackground = Black,

    outline = Gray300,

    transparent = Color.Transparent,
    white = White,
    black = Black,

    text = Black,
    textSecondary = Gray700,
    textDisabled = Gray400,

    scrim = Color.Black.copy(alpha = 0.32f),
    elevation = Gray700,
)

internal val DarkColors = Colors(
    primary = White,
    onPrimary = Black,

    secondary = Gray400,
    onSecondary = White,

    tertiary = Blue300,
    onTertiary = Black,

    surface = Gray900,
    onSurface = White,

    error = Red400,
    onError = Black,

    disabled = Gray700,
    onDisabled = Gray500,

    background = Black,
    onBackground = White,

    outline = Gray800,

    transparent = Color.Transparent,
    white = White,
    black = Black,

    text = White,
    textSecondary = Gray300,
    textDisabled = Gray600,

    scrim = Color.Black.copy(alpha = 0.72f),
    elevation = Gray200
)

val LocalColors = staticCompositionLocalOf { LightColors }
val LocalContentColor = compositionLocalOf { Color.Black }
val LocalContentAlpha = compositionLocalOf { 1f }

fun Colors.contentColorFor(backgroundColor: Color): Color {
    return when (backgroundColor) {
        primary -> onPrimary
        secondary -> onSecondary
        tertiary -> onTertiary
        surface -> onSurface
        error -> onError
        disabled -> onDisabled
        background -> onBackground
        else -> Color.Unspecified
    }
}
