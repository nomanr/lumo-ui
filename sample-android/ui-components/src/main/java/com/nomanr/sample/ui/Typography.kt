package com.nomanr.sample.ui

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nomanr.lumo.sample.ui_components.R

val fontFamily = FontFamily(
    Font(R.font.poppins_black, FontWeight.Black),
    Font(R.font.poppins_extrabold, FontWeight.ExtraBold),
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_regular, FontWeight.Normal)
)

data class Typography(
    val h1: TextStyle = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    val h2: TextStyle = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    val h3: TextStyle = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),

    val h4: TextStyle = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),

    val body1: TextStyle = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    val body2: TextStyle = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.15.sp
    ),
    val body3: TextStyle = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.15.sp
    ),
    val label1: TextStyle = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    val label2: TextStyle = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    val label3: TextStyle = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 10.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.5.sp
    ),

    val button: TextStyle = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 1.sp
    ),
    val input: TextStyle = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
)

val typography = Typography()

val LocalTypography = staticCompositionLocalOf { typography }
val LocalOriginalTypography = staticCompositionLocalOf { typography }

fun scaledTypography(fontScale: Float): Typography {
    return Typography(
        h1 = typography.h1.copy(fontSize = 24.sp * fontScale, lineHeight = 32.sp * fontScale),
        h2 = typography.h2.copy(fontSize = 20.sp * fontScale, lineHeight = 28.sp * fontScale),
        h3 = typography.h3.copy(fontSize = 16.sp * fontScale, lineHeight = 24.sp * fontScale),
        h4 = typography.h4.copy(fontSize = 16.sp * fontScale, lineHeight = 24.sp * fontScale),
        body1 = typography.body1.copy(fontSize = 16.sp * fontScale, lineHeight = 24.sp * fontScale),
        body2 = typography.body2.copy(fontSize = 14.sp * fontScale, lineHeight = 20.sp * fontScale),
        body3 = typography.body3.copy(fontSize = 12.sp * fontScale, lineHeight = 16.sp * fontScale),
        label1 = typography.label1.copy(fontSize = 14.sp * fontScale, lineHeight = 20.sp * fontScale),
        label2 = typography.label2.copy(fontSize = 12.sp * fontScale, lineHeight = 16.sp * fontScale),
        label3 = typography.label3.copy(fontSize = 10.sp * fontScale, lineHeight = 12.sp * fontScale),
        button = typography.button.copy(fontSize = 14.sp * fontScale, lineHeight = 20.sp * fontScale),
        input = typography.input.copy(fontSize = 16.sp * fontScale, lineHeight = 24.sp * fontScale),
    )
}
