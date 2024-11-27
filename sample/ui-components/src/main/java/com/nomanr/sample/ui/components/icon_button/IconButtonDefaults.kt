package com.nomanr.sample.ui.components.icon_button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.foundation.ButtonElevation

internal object IconButtonDefaults {
    internal val ButtonSize = 44.dp

    internal val ButtonPadding = PaddingValues(4.dp)
    internal val ButtonSquareShape = RoundedCornerShape(12)
    internal val ButtonCircleShape = RoundedCornerShape(50)
    internal val OutlineHeight = 1.dp


    @Composable
    fun buttonElevation() = ButtonElevation(
        defaultElevation = 2.dp,
        pressedElevation = 2.dp,
        focusedElevation = 2.dp,
        hoveredElevation = 2.dp,
        disabledElevation = 0.dp
    )

    @Composable
    fun primaryFilled(shape: Shape) = ButtonStyle(
        colors = ButtonColors(
            containerColor = AppTheme.colors.primary,
            contentColor = AppTheme.colors.onPrimary,
            disabledContainerColor = AppTheme.colors.disabled,
            disabledContentColor = AppTheme.colors.onDisabled,
        ),
        shape = shape,
        elevation = null,
        contentPadding = ButtonPadding,
    )

    @Composable
    fun primaryElevated(shape: Shape) = ButtonStyle(
        colors = ButtonColors(
            containerColor = AppTheme.colors.primary,
            contentColor = AppTheme.colors.onPrimary,
            disabledContainerColor = AppTheme.colors.disabled,
            disabledContentColor = AppTheme.colors.onDisabled,
        ),
        shape = shape,
        elevation = buttonElevation(),
        contentPadding = ButtonPadding,
    )

    @Composable
    fun primaryOutlined(shape: Shape) = ButtonStyle(
        colors = ButtonColors(
            containerColor = AppTheme.colors.transparent,
            contentColor = AppTheme.colors.primary,
            borderColor = AppTheme.colors.primary,
            disabledContainerColor = AppTheme.colors.transparent,
            disabledContentColor = AppTheme.colors.onDisabled,
            disabledBorderColor = AppTheme.colors.disabled,
        ),
        shape = shape,
        elevation = null,
        contentPadding = ButtonPadding,
    )

    @Composable
    fun primaryGhost(shape: Shape) = ButtonStyle(
        colors = ButtonColors(
            containerColor = AppTheme.colors.transparent,
            contentColor = AppTheme.colors.primary,
            borderColor = AppTheme.colors.transparent,
            disabledContainerColor = AppTheme.colors.transparent,
            disabledContentColor = AppTheme.colors.onDisabled,
            disabledBorderColor = AppTheme.colors.transparent,
        ),
        shape = shape,
        elevation = null,
        contentPadding = ButtonPadding,
    )

    @Composable
    fun secondaryFilled(shape: Shape) = ButtonStyle(
        colors = ButtonColors(
            containerColor = AppTheme.colors.secondary,
            contentColor = AppTheme.colors.onSecondary,
            disabledContainerColor = AppTheme.colors.disabled,
            disabledContentColor = AppTheme.colors.onDisabled,
        ),
        shape = shape,
        elevation = null,
        contentPadding = ButtonPadding,
    )

    @Composable
    fun secondaryElevated(shape: Shape) = ButtonStyle(
        colors = ButtonColors(
            containerColor = AppTheme.colors.secondary,
            contentColor = AppTheme.colors.onSecondary,
            disabledContainerColor = AppTheme.colors.disabled,
            disabledContentColor = AppTheme.colors.onDisabled,
        ),
        shape = shape,
        elevation = buttonElevation(),
        contentPadding = ButtonPadding,
    )

    @Composable
    fun secondaryOutlined(shape: Shape) = ButtonStyle(
        colors = ButtonColors(
            containerColor = AppTheme.colors.transparent,
            contentColor = AppTheme.colors.primary,
            borderColor = AppTheme.colors.secondary,
            disabledContainerColor = AppTheme.colors.transparent,
            disabledContentColor = AppTheme.colors.onDisabled,
            disabledBorderColor = AppTheme.colors.disabled,
        ),
        shape = shape,
        elevation = null,
        contentPadding = ButtonPadding,
    )

    @Composable
    fun secondaryGhost(shape: Shape) = ButtonStyle(
        colors = ButtonColors(
            containerColor = AppTheme.colors.transparent,
            contentColor = AppTheme.colors.primary,
            borderColor = AppTheme.colors.transparent,
            disabledContainerColor = AppTheme.colors.transparent,
            disabledContentColor = AppTheme.colors.onDisabled,
            disabledBorderColor = AppTheme.colors.transparent,
        ),
        shape = shape,
        elevation = null,
        contentPadding = ButtonPadding,
    )

    @Composable
    fun destructiveFilled(shape: Shape) = ButtonStyle(
        colors = ButtonColors(
            containerColor = AppTheme.colors.error,
            contentColor = AppTheme.colors.onError,
            disabledContainerColor = AppTheme.colors.disabled,
            disabledContentColor = AppTheme.colors.onDisabled,
        ),
        shape = shape,
        elevation = null,
        contentPadding = ButtonPadding,
    )

    @Composable
    fun destructiveElevated(shape: Shape) = ButtonStyle(
        colors = ButtonColors(
            containerColor = AppTheme.colors.error,
            contentColor = AppTheme.colors.onError,
            disabledContainerColor = AppTheme.colors.disabled,
            disabledContentColor = AppTheme.colors.onDisabled,
        ),
        shape = shape,
        elevation = buttonElevation(),
        contentPadding = ButtonPadding,
    )

    @Composable
    fun destructiveOutlined(shape: Shape) = ButtonStyle(
        colors = ButtonColors(
            containerColor = AppTheme.colors.transparent,
            contentColor = AppTheme.colors.error,
            borderColor = AppTheme.colors.error,
            disabledContainerColor = AppTheme.colors.transparent,
            disabledContentColor = AppTheme.colors.onDisabled,
            disabledBorderColor = AppTheme.colors.disabled,
        ),
        shape = shape,
        elevation = null,
        contentPadding = ButtonPadding,
    )

    @Composable
    fun destructiveGhost(shape: Shape) = ButtonStyle(
        colors = ButtonColors(
            containerColor = AppTheme.colors.transparent,
            contentColor = AppTheme.colors.error,
            borderColor = AppTheme.colors.transparent,
            disabledContainerColor = AppTheme.colors.transparent,
            disabledContentColor = AppTheme.colors.onDisabled,
            disabledBorderColor = AppTheme.colors.transparent,
        ),
        shape = shape,
        elevation = null,
        contentPadding = ButtonPadding,
    )
}

@Immutable
internal data class ButtonColors(
    val containerColor: Color,
    val contentColor: Color,
    val borderColor: Color? = null,
    val disabledContainerColor: Color,
    val disabledContentColor: Color,
    val disabledBorderColor: Color? = null,
) {
    @Composable
    internal fun containerColor(enabled: Boolean) =
        rememberUpdatedState(newValue = if (enabled) containerColor else disabledContainerColor)

    @Composable
    internal fun contentColor(enabled: Boolean) =
        rememberUpdatedState(newValue = if (enabled) contentColor else disabledContentColor)

    @Composable
    fun borderColor(enabled: Boolean) =
        rememberUpdatedState(newValue = if (enabled) borderColor else disabledBorderColor)
}

@Immutable
internal data class ButtonStyle(
    val colors: ButtonColors,
    val shape: Shape,
    val elevation: ButtonElevation? = null,
    val contentPadding: PaddingValues,
)
