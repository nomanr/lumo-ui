package com.nomanr.compose.ui.foundation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.nomanr.compose.ui.LocalContentColor
import com.nomanr.compose.ui.components.LocalTextStyle

@Composable
fun ProvideTextStyle(value: TextStyle, content: @Composable () -> Unit) {
    val mergedStyle = LocalTextStyle.current.merge(value)
    CompositionLocalProvider(LocalTextStyle provides mergedStyle, content = content)
}

@Composable
internal fun ProvideContentColorTextStyle(
    contentColor: Color,
    textStyle: TextStyle,
    content: @Composable () -> Unit
) {
    val mergedStyle = LocalTextStyle.current.merge(textStyle)
    CompositionLocalProvider(
        LocalContentColor provides contentColor,
        LocalTextStyle provides mergedStyle,
        content = content
    )
}
