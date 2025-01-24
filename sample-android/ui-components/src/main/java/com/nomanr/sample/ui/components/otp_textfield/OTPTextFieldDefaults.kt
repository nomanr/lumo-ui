package com.nomanr.sample.ui.components.otp_textfield

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.AppTheme

enum class OTPTextFieldType {
    Outlined,
    Filled,
    Underlined
}

internal object OTPTextFieldDefaults {
    val OTPTextFieldShape: Shape = RoundedCornerShape(8.dp)
    const val OTPLength = 6
    val ItemWidth = 48.dp
    val ItemHeight = 48.dp
    private val UnfocusedOutlineThickness = 2.dp
    private val FocusedOutlineThickness = 2.dp
    val ItemSpacing = 8.dp


    @Composable
    fun outlinedColors() = OTPTextFieldColors(
        focusedTextColor = AppTheme.colors.text,
        unfocusedTextColor = AppTheme.colors.text,
        disabledTextColor = AppTheme.colors.onDisabled,
        errorTextColor = AppTheme.colors.text,
        focusedContainerColor = AppTheme.colors.transparent,
        unfocusedContainerColor = AppTheme.colors.transparent,
        disabledContainerColor = AppTheme.colors.transparent,
        errorContainerColor = AppTheme.colors.transparent,
        cursorColor = AppTheme.colors.primary,
        errorCursorColor = AppTheme.colors.error,
        textSelectionColors = LocalTextSelectionColors.current,
        focusedOutlineColor = AppTheme.colors.primary,
        unfocusedOutlineColor = AppTheme.colors.secondary,
        disabledOutlineColor = AppTheme.colors.disabled,
        errorOutlineColor = AppTheme.colors.error,
    )

    @Composable
    fun filledColors() = OTPTextFieldColors(
        focusedTextColor = AppTheme.colors.text,
        unfocusedTextColor = AppTheme.colors.text,
        disabledTextColor = AppTheme.colors.onDisabled,
        errorTextColor = AppTheme.colors.text,
        focusedContainerColor = AppTheme.colors.secondary,
        unfocusedContainerColor = AppTheme.colors.secondary,
        disabledContainerColor = AppTheme.colors.disabled,
        errorContainerColor = AppTheme.colors.secondary,
        cursorColor = AppTheme.colors.primary,
        errorCursorColor = AppTheme.colors.error,
        textSelectionColors = LocalTextSelectionColors.current,
        focusedOutlineColor = AppTheme.colors.transparent,
        unfocusedOutlineColor = AppTheme.colors.transparent,
        disabledOutlineColor = AppTheme.colors.transparent,
        errorOutlineColor = AppTheme.colors.error,
    )

    @Composable
    fun underlinedColors() = OTPTextFieldColors(
        focusedTextColor = AppTheme.colors.text,
        unfocusedTextColor = AppTheme.colors.text,
        disabledTextColor = AppTheme.colors.onDisabled,
        errorTextColor = AppTheme.colors.text,
        focusedContainerColor = AppTheme.colors.transparent,
        unfocusedContainerColor = AppTheme.colors.transparent,
        disabledContainerColor = AppTheme.colors.transparent,
        errorContainerColor = AppTheme.colors.transparent,
        cursorColor = AppTheme.colors.primary,
        errorCursorColor = AppTheme.colors.error,
        textSelectionColors = LocalTextSelectionColors.current,
        focusedOutlineColor = AppTheme.colors.primary,
        unfocusedOutlineColor = AppTheme.colors.secondary,
        disabledOutlineColor = AppTheme.colors.disabled,
        errorOutlineColor = AppTheme.colors.error,
    )

    @Composable
    fun containerBorderThickness(
        interactionSource: InteractionSource,
    ): Dp {
        val focused by interactionSource.collectIsFocusedAsState()

        return if (focused) FocusedOutlineThickness else UnfocusedOutlineThickness
    }


    @Composable
    fun DecorationBox(
        value: String,
        innerTextField: @Composable () -> Unit,
        visualTransformation: VisualTransformation,
        enabled: Boolean = true,
        isError: Boolean = false,
        interactionSource: InteractionSource,
        colors: OTPTextFieldColors,
        type: OTPTextFieldType,
    ) {
        val transformedText = remember(value, visualTransformation) {
            visualTransformation.filter(AnnotatedString(value))
        }.text.text

        val borderThickness = containerBorderThickness(interactionSource)

        val containerModifier = when (type) {
            OTPTextFieldType.Underlined -> Modifier.containerUnderline(
                transformedText, enabled, isError, interactionSource, colors, borderThickness
            )

            else -> Modifier.containerOutline(transformedText, enabled, isError, interactionSource, colors, borderThickness)
        }

        Box(
            modifier = Modifier
                .background(colors.containerColor(enabled, isError, interactionSource).value, OTPTextFieldShape)
                .defaultMinSize(minHeight = ItemHeight)
                .then(containerModifier)

        ) {
            Box(
                modifier = Modifier.align(Alignment.Center)
            ) {
                innerTextField()
            }
        }
    }
}

@Composable
internal fun animateTextFieldBorderAsState(
    value: String,
    enabled: Boolean,
    isError: Boolean,
    interactionSource: InteractionSource,
    colors: OTPTextFieldColors,
    borderThickness: Dp,
): State<BorderStroke> {
    val indicatorColor = colors.containerOutlineColor(value, enabled, isError, interactionSource)

    return rememberUpdatedState(
        BorderStroke(borderThickness, SolidColor(indicatorColor.value))
    )
}

internal fun Modifier.containerOutline(
    value: String,
    enabled: Boolean,
    isError: Boolean,
    interactionSource: InteractionSource,
    colors: OTPTextFieldColors,
    borderThickness: Dp,

    ) = composed(inspectorInfo = debugInspectorInfo {
    name = "indicatorLine"
    properties["value"] = value
    properties["enabled"] = enabled
    properties["isError"] = isError
    properties["interactionSource"] = interactionSource
    properties["colors"] = colors
    properties["borderThickness"] = borderThickness
}) {
    val stroke = animateTextFieldBorderAsState(
        value,
        enabled,
        isError,
        interactionSource,
        colors,
        borderThickness,
    )

    this.then(Modifier.border(stroke.value, shape = OTPTextFieldDefaults.OTPTextFieldShape))
}

internal fun Modifier.containerUnderline(
    value: String,
    enabled: Boolean,
    isError: Boolean,
    interactionSource: InteractionSource,
    colors: OTPTextFieldColors,
    borderThickness: Dp,
) = composed {
    val indicatorColor = colors.containerOutlineColor(value, enabled, isError, interactionSource)

    this.then(Modifier.drawBehind {
        val strokeWidthPx = borderThickness.toPx()
        drawLine(
            color = indicatorColor.value,
            start = androidx.compose.ui.geometry.Offset(0f, size.height - strokeWidthPx / 2),
            end = androidx.compose.ui.geometry.Offset(size.width, size.height - strokeWidthPx / 2),
            strokeWidth = strokeWidthPx
        )
    })
}


@Immutable
data class OTPTextFieldColors(
    val focusedTextColor: Color,
    val unfocusedTextColor: Color,
    val disabledTextColor: Color,
    val errorTextColor: Color,
    val focusedContainerColor: Color,
    val unfocusedContainerColor: Color,
    val disabledContainerColor: Color,
    val errorContainerColor: Color,
    val cursorColor: Color,
    val errorCursorColor: Color,
    val textSelectionColors: TextSelectionColors,
    val focusedOutlineColor: Color,
    val unfocusedOutlineColor: Color,
    val disabledOutlineColor: Color,
    val errorOutlineColor: Color,
) {

    @Composable
    internal fun containerColor(
        enabled: Boolean, isError: Boolean, interactionSource: InteractionSource
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()

        return rememberUpdatedState(
            when {
                !enabled -> disabledContainerColor
                isError -> errorContainerColor
                focused -> focusedContainerColor
                else -> unfocusedContainerColor
            }
        )
    }

    @Composable
    fun containerOutlineColor(
        value: String, enabled: Boolean, isError: Boolean, interactionSource: InteractionSource
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()
        return rememberUpdatedState(
            when {
                !enabled -> disabledOutlineColor
                isError -> errorOutlineColor
                value.trim().isNotEmpty() -> focusedOutlineColor
                focused -> focusedOutlineColor
                else -> unfocusedOutlineColor
            }
        )
    }

    @Composable
    internal fun textColor(
        enabled: Boolean, isError: Boolean, interactionSource: InteractionSource
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()

        return rememberUpdatedState(
            when {
                !enabled -> disabledTextColor
                isError -> errorTextColor
                focused -> focusedTextColor
                else -> unfocusedTextColor
            }
        )
    }

    @Composable
    internal fun cursorColor(isError: Boolean): State<Color> {
        return rememberUpdatedState(if (isError) errorCursorColor else cursorColor)
    }

    internal val selectionColors: TextSelectionColors
        @Composable get() = textSelectionColors
}


