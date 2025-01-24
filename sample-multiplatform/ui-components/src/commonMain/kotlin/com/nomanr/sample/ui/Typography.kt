package com.nomanr.sample.ui

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
import lumo_ui.sample_multiplatform.ui_components.generated.resources.poppins_medium
import lumo_ui.sample_multiplatform.ui_components.generated.resources.poppins_regular
import lumo_ui.sample_multiplatform.ui_components.generated.resources.poppins_semibold
import org.jetbrains.compose.resources.Font

val fontFamily: FontFamily
    @Composable get() = FontFamily(
        Font(Res.font.poppins_black, FontWeight.Black),
        Font(Res.font.poppins_extrabold, FontWeight.ExtraBold),
        Font(Res.font.poppins_bold, FontWeight.Bold),
        Font(Res.font.poppins_semibold, FontWeight.SemiBold),
        Font(Res.font.poppins_medium, FontWeight.Medium),
        Font(Res.font.poppins_regular, FontWeight.Normal)
    )

val baseTypography = androidx.compose.material.Typography()

data class Typography(
    val h1: TextStyle = baseTypography.h1,
    val h2: TextStyle = baseTypography.h2,
    val h3: TextStyle = baseTypography.h3,
    val h4: TextStyle = baseTypography.h4,
    val body1: TextStyle = baseTypography.h5,
    val body2: TextStyle = baseTypography.body1,
    val body3: TextStyle = baseTypography.body2,
    val label1: TextStyle = baseTypography.subtitle1,
    val label2: TextStyle = baseTypography.subtitle2,
    val label3: TextStyle = baseTypography.caption,
    val button: TextStyle = baseTypography.button,
    val input: TextStyle = baseTypography.overline
)

@Composable
fun Typo(): Typography {
    return Typography(
        h1 = baseTypography.h1.copy(fontFamily = fontFamily),
        h2 = baseTypography.h2.copy(fontFamily = fontFamily),
//        TextStyle(
//            fontFamily = fontFamily,
//            fontWeight = FontWeight.Bold,
//            fontSize = 24.sp,
//            lineHeight = 32.sp,
//            letterSpacing = 0.sp,
//        ),
//        h2 = TextStyle(
//            fontFamily = fontFamily,
//            fontWeight = FontWeight.Bold,
//            fontSize = 20.sp,
//            lineHeight = 28.sp,
//            letterSpacing = 0.sp
//        ),
        h3 = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.sp
        ),
        h4 = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.sp
        ),
        body1 = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.sp
        ),
        body2 = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.15.sp
        ),
        body3 = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.15.sp
        ),
        label1 = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.W500,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),
        label2 = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.W500,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        ),
        label3 = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.W500,
            fontSize = 10.sp,
            lineHeight = 12.sp,
            letterSpacing = 0.5.sp
        ),
        button = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.W500,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 1.sp
        ),
        input = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.sp
        )
    )
}

val typography = @Composable { Typo() }

val LocalTypography = staticCompositionLocalOf {
    Typography()
}
val LocalTextStyle = compositionLocalOf(structuralEqualityPolicy()) { TextStyle.Default }