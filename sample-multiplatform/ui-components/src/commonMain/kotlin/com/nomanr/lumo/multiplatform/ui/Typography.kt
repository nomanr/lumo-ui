package com.nomanr.lumo.multiplatform.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import lumo_ui.sample_multiplatform.ui_components.generated.resources.Res
import lumo_ui.sample_multiplatform.ui_components.generated.resources.poppins_black
import lumo_ui.sample_multiplatform.ui_components.generated.resources.poppins_bold
import lumo_ui.sample_multiplatform.ui_components.generated.resources.poppins_extrabold
import lumo_ui.sample_multiplatform.ui_components.generated.resources.poppins_extralight
import lumo_ui.sample_multiplatform.ui_components.generated.resources.poppins_light
import lumo_ui.sample_multiplatform.ui_components.generated.resources.poppins_medium
import lumo_ui.sample_multiplatform.ui_components.generated.resources.poppins_regular
import lumo_ui.sample_multiplatform.ui_components.generated.resources.poppins_semibold
import lumo_ui.sample_multiplatform.ui_components.generated.resources.poppins_thin
import org.jetbrains.compose.resources.Font

@Composable
fun fontFamily() =
    FontFamily(
        Font(Res.font.poppins_black, FontWeight.Black),
        Font(Res.font.poppins_extrabold, FontWeight.ExtraBold),
        Font(Res.font.poppins_bold, FontWeight.Bold),
        Font(Res.font.poppins_semibold, FontWeight.SemiBold),
        Font(Res.font.poppins_medium, FontWeight.Medium),
        Font(Res.font.poppins_regular, FontWeight.Normal),
        Font(Res.font.poppins_light, FontWeight.Light),
        Font(Res.font.poppins_extralight, FontWeight.ExtraLight),
        Font(Res.font.poppins_thin, FontWeight.Thin),
    )

data class Typography(
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val h4: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
    val body3: TextStyle,
    val label1: TextStyle,
    val label2: TextStyle,
    val label3: TextStyle,
    val button: TextStyle,
    val input: TextStyle,
)

private val defaultTypography =
    Typography(
        h1 =
            TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                lineHeight = 32.sp,
                letterSpacing = 0.sp,
            ),
        h2 =
            TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                lineHeight = 28.sp,
                letterSpacing = 0.sp,
            ),
        h3 =
            TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.sp,
            ),
        h4 =
            TextStyle(
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.sp,
            ),
        body1 =
            TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.sp,
            ),
        body2 =
            TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.15.sp,
            ),
        body3 =
            TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.15.sp,
            ),
        label1 =
            TextStyle(
                fontWeight = FontWeight.W500,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.1.sp,
            ),
        label2 =
            TextStyle(
                fontWeight = FontWeight.W500,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.5.sp,
            ),
        label3 =
            TextStyle(
                fontWeight = FontWeight.W500,
                fontSize = 10.sp,
                lineHeight = 12.sp,
                letterSpacing = 0.5.sp,
            ),
        button =
            TextStyle(
                fontWeight = FontWeight.W500,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 1.sp,
            ),
        input =
            TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.sp,
            ),
    )

@Composable
fun provideTypography(): Typography {
    val fontFamily = fontFamily()

    return defaultTypography.copy(
        h1 = defaultTypography.h1.copy(fontFamily = fontFamily),
        h2 = defaultTypography.h2.copy(fontFamily = fontFamily),
        h3 = defaultTypography.h3.copy(fontFamily = fontFamily),
        h4 = defaultTypography.h4.copy(fontFamily = fontFamily),
        body1 = defaultTypography.body1.copy(fontFamily = fontFamily),
        body2 = defaultTypography.body2.copy(fontFamily = fontFamily),
        body3 = defaultTypography.body3.copy(fontFamily = fontFamily),
        label1 = defaultTypography.label1.copy(fontFamily = fontFamily),
        label2 = defaultTypography.label2.copy(fontFamily = fontFamily),
        label3 = defaultTypography.label3.copy(fontFamily = fontFamily),
        button = defaultTypography.button.copy(fontFamily = fontFamily),
        input = defaultTypography.input.copy(fontFamily = fontFamily),
    )
}

val LocalTypography = staticCompositionLocalOf { defaultTypography }
val LocalTextStyle = compositionLocalOf(structuralEqualityPolicy()) { TextStyle.Default }
val LocalOriginalTypography = staticCompositionLocalOf { defaultTypography }

fun scaledTypography(fontScale: Float, default: Typography): Typography {
    return Typography(
        h1 = default.h1.copy(fontSize = 24.sp * fontScale, lineHeight = 32.sp * fontScale),
        h2 = default.h2.copy(fontSize = 20.sp * fontScale, lineHeight = 28.sp * fontScale),
        h3 = default.h3.copy(fontSize = 16.sp * fontScale, lineHeight = 24.sp * fontScale),
        h4 = default.h4.copy(fontSize = 16.sp * fontScale, lineHeight = 24.sp * fontScale),
        body1 = default.body1.copy(fontSize = 16.sp * fontScale, lineHeight = 24.sp * fontScale),
        body2 = default.body2.copy(fontSize = 14.sp * fontScale, lineHeight = 20.sp * fontScale),
        body3 = default.body3.copy(fontSize = 12.sp * fontScale, lineHeight = 16.sp * fontScale),
        label1 = default.label1.copy(fontSize = 14.sp * fontScale, lineHeight = 20.sp * fontScale),
        label2 = default.label2.copy(fontSize = 12.sp * fontScale, lineHeight = 16.sp * fontScale),
        label3 = default.label3.copy(fontSize = 10.sp * fontScale, lineHeight = 12.sp * fontScale),
        button = default.button.copy(fontSize = 14.sp * fontScale, lineHeight = 20.sp * fontScale),
        input = default.input.copy(fontSize = 16.sp * fontScale, lineHeight = 24.sp * fontScale),
    )
}
