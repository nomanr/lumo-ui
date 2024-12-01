package com.nomanr.compose.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nomanr.compose.ui.AppTheme
import com.nomanr.compose.ui.foundation.ButtonElevation

@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    variant: IconButtonVariant = IconButtonVariant.PrimaryFilled,
    shape: Shape = IconButtonDefaults.ButtonSquareShape,
    onClick: () -> Unit = {},
    contentPadding: PaddingValues = IconButtonDefaults.ButtonPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit
) {
    val style = IconButtonDefaults.styleFor(variant, shape)

    IconButtonComponent(
        modifier = modifier,
        enabled = enabled,
        loading = loading,
        style = style,
        onClick = onClick,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content
    )
}

@Composable
private fun IconButtonComponent(
    modifier: Modifier,
    enabled: Boolean,
    loading: Boolean,
    style: IconButtonStyle,
    onClick: () -> Unit,
    contentPadding: PaddingValues,
    interactionSource: MutableInteractionSource,
    content: @Composable () -> Unit
) {
    val containerColor = style.colors.containerColor(enabled).value
    val contentColor = style.colors.contentColor(enabled).value
    val borderColor = style.colors.borderColor(enabled).value
    val borderStroke = if (borderColor != null) BorderStroke(IconButtonDefaults.OutlineHeight, borderColor) else null

    val shadowElevation = style.elevation?.shadowElevation(enabled, interactionSource)?.value ?: 0.dp

    Surface(
        onClick = onClick,
        modifier = modifier
            .defaultMinSize(
                minWidth = IconButtonDefaults.ButtonSize,
                minHeight = IconButtonDefaults.ButtonSize
            )
            .semantics { role = Role.Button },
        enabled = enabled,
        shape = style.shape,
        color = containerColor,
        contentColor = contentColor,
        border = borderStroke,
        shadowElevation = shadowElevation,
        interactionSource = interactionSource
    ) {
        Box(
            modifier = Modifier.padding(contentPadding),
            contentAlignment = Alignment.Center
        ) {
            // Add a loading indicator if needed
            if (!loading) {
                content()
            }
        }
    }
}

enum class IconButtonVariant {
    PrimaryFilled,
    PrimaryOutlined,
    PrimaryElevated,
    PrimaryGhost,
    SecondaryFilled,
    SecondaryOutlined,
    SecondaryElevated,
    SecondaryGhost,
    DestructiveFilled,
    DestructiveOutlined,
    DestructiveElevated,
    DestructiveGhost
}

internal object IconButtonDefaults {
    val ButtonSize = 44.dp
    val ButtonPadding = PaddingValues(4.dp)
    val ButtonSquareShape = RoundedCornerShape(12.dp)
    val ButtonCircleShape = RoundedCornerShape(percent = 50)
    val OutlineHeight = 1.dp

    @Composable
    fun buttonElevation() = ButtonElevation(
        defaultElevation = 2.dp,
        pressedElevation = 2.dp,
        focusedElevation = 2.dp,
        hoveredElevation = 2.dp,
        disabledElevation = 0.dp
    )

    @Composable
    fun styleFor(variant: IconButtonVariant, shape: Shape): IconButtonStyle {
        return when (variant) {
            IconButtonVariant.PrimaryFilled -> primaryFilled(shape)
            IconButtonVariant.PrimaryOutlined -> primaryOutlined(shape)
            IconButtonVariant.PrimaryElevated -> primaryElevated(shape)
            IconButtonVariant.PrimaryGhost -> primaryGhost(shape)
            IconButtonVariant.SecondaryFilled -> secondaryFilled(shape)
            IconButtonVariant.SecondaryOutlined -> secondaryOutlined(shape)
            IconButtonVariant.SecondaryElevated -> secondaryElevated(shape)
            IconButtonVariant.SecondaryGhost -> secondaryGhost(shape)
            IconButtonVariant.DestructiveFilled -> destructiveFilled(shape)
            IconButtonVariant.DestructiveOutlined -> destructiveOutlined(shape)
            IconButtonVariant.DestructiveElevated -> destructiveElevated(shape)
            IconButtonVariant.DestructiveGhost -> destructiveGhost(shape)
        }
    }

    @Composable
    fun primaryFilled(shape: Shape) = IconButtonStyle(
        colors = IconButtonColors(
            containerColor = AppTheme.colors.primary,
            contentColor = AppTheme.colors.onPrimary,
            disabledContainerColor = AppTheme.colors.disabled,
            disabledContentColor = AppTheme.colors.onDisabled
        ),
        shape = shape,
        elevation = null
    )

    @Composable
    fun primaryOutlined(shape: Shape) = IconButtonStyle(
        colors = IconButtonColors(
            containerColor = AppTheme.colors.transparent,
            contentColor = AppTheme.colors.primary,
            borderColor = AppTheme.colors.primary,
            disabledContainerColor = AppTheme.colors.transparent,
            disabledContentColor = AppTheme.colors.onDisabled,
            disabledBorderColor = AppTheme.colors.disabled
        ),
        shape = shape,
        elevation = null
    )

    @Composable
    fun primaryElevated(shape: Shape) = IconButtonStyle(
        colors = IconButtonColors(
            containerColor = AppTheme.colors.primary,
            contentColor = AppTheme.colors.onPrimary,
            disabledContainerColor = AppTheme.colors.disabled,
            disabledContentColor = AppTheme.colors.onDisabled
        ),
        shape = shape,
        elevation = buttonElevation()
    )

    @Composable
    fun primaryGhost(shape: Shape) = IconButtonStyle(
        colors = IconButtonColors(
            containerColor = AppTheme.colors.transparent,
            contentColor = AppTheme.colors.primary,
            borderColor = AppTheme.colors.transparent,
            disabledContainerColor = AppTheme.colors.transparent,
            disabledContentColor = AppTheme.colors.onDisabled,
            disabledBorderColor = AppTheme.colors.transparent
        ),
        shape = shape,
        elevation = null
    )

    @Composable
    fun secondaryFilled(shape: Shape) = IconButtonStyle(
        colors = IconButtonColors(
            containerColor = AppTheme.colors.secondary,
            contentColor = AppTheme.colors.onSecondary,
            disabledContainerColor = AppTheme.colors.disabled,
            disabledContentColor = AppTheme.colors.onDisabled
        ),
        shape = shape,
        elevation = null
    )

    @Composable
    fun secondaryOutlined(shape: Shape) = IconButtonStyle(
        colors = IconButtonColors(
            containerColor = AppTheme.colors.transparent,
            contentColor = AppTheme.colors.secondary,
            borderColor = AppTheme.colors.secondary,
            disabledContainerColor = AppTheme.colors.transparent,
            disabledContentColor = AppTheme.colors.onDisabled,
            disabledBorderColor = AppTheme.colors.disabled
        ),
        shape = shape,
        elevation = null
    )

    @Composable
    fun secondaryElevated(shape: Shape) = IconButtonStyle(
        colors = IconButtonColors(
            containerColor = AppTheme.colors.secondary,
            contentColor = AppTheme.colors.onSecondary,
            disabledContainerColor = AppTheme.colors.disabled,
            disabledContentColor = AppTheme.colors.onDisabled
        ),
        shape = shape,
        elevation = buttonElevation()
    )

    @Composable
    fun secondaryGhost(shape: Shape) = IconButtonStyle(
        colors = IconButtonColors(
            containerColor = AppTheme.colors.transparent,
            contentColor = AppTheme.colors.secondary,
            borderColor = AppTheme.colors.transparent,
            disabledContainerColor = AppTheme.colors.transparent,
            disabledContentColor = AppTheme.colors.onDisabled,
            disabledBorderColor = AppTheme.colors.transparent
        ),
        shape = shape,
        elevation = null
    )

    @Composable
    fun destructiveFilled(shape: Shape) = IconButtonStyle(
        colors = IconButtonColors(
            containerColor = AppTheme.colors.error,
            contentColor = AppTheme.colors.onError,
            disabledContainerColor = AppTheme.colors.disabled,
            disabledContentColor = AppTheme.colors.onDisabled
        ),
        shape = shape,
        elevation = null
    )

    @Composable
    fun destructiveOutlined(shape: Shape) = IconButtonStyle(
        colors = IconButtonColors(
            containerColor = AppTheme.colors.transparent,
            contentColor = AppTheme.colors.error,
            borderColor = AppTheme.colors.error,
            disabledContainerColor = AppTheme.colors.transparent,
            disabledContentColor = AppTheme.colors.onDisabled,
            disabledBorderColor = AppTheme.colors.disabled
        ),
        shape = shape,
        elevation = null
    )

    @Composable
    fun destructiveElevated(shape: Shape) = IconButtonStyle(
        colors = IconButtonColors(
            containerColor = AppTheme.colors.error,
            contentColor = AppTheme.colors.onError,
            disabledContainerColor = AppTheme.colors.disabled,
            disabledContentColor = AppTheme.colors.onDisabled
        ),
        shape = shape,
        elevation = buttonElevation()
    )

    @Composable
    fun destructiveGhost(shape: Shape) = IconButtonStyle(
        colors = IconButtonColors(
            containerColor = AppTheme.colors.transparent,
            contentColor = AppTheme.colors.error,
            borderColor = AppTheme.colors.transparent,
            disabledContainerColor = AppTheme.colors.transparent,
            disabledContentColor = AppTheme.colors.onDisabled,
            disabledBorderColor = AppTheme.colors.transparent
        ),
        shape = shape,
        elevation = null
    )
}

@Immutable
data class IconButtonColors(
    val containerColor: Color,
    val contentColor: Color,
    val borderColor: Color? = null,
    val disabledContainerColor: Color,
    val disabledContentColor: Color,
    val disabledBorderColor: Color? = null
) {
    @Composable
    fun containerColor(enabled: Boolean) =
        rememberUpdatedState(if (enabled) containerColor else disabledContainerColor)

    @Composable
    fun contentColor(enabled: Boolean) =
        rememberUpdatedState(if (enabled) contentColor else disabledContentColor)

    @Composable
    fun borderColor(enabled: Boolean) =
        rememberUpdatedState(if (enabled) borderColor else disabledBorderColor)
}

@Immutable
data class IconButtonStyle(
    val colors: IconButtonColors,
    val shape: Shape,
    val elevation: ButtonElevation? = null
)

@Composable
@Preview("Primary Icon Buttons")
fun PrimaryIconButtonPreview() {
    AppTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Primary Icon Buttons", style = AppTheme.typography.h2)

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                IconButton(variant = IconButtonVariant.PrimaryFilled) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "PrimaryFilled")
                }
                IconButton(variant = IconButtonVariant.PrimaryOutlined) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "PrimaryOutlined")
                }
                IconButton(variant = IconButtonVariant.PrimaryElevated) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "PrimaryElevated")
                }
                IconButton(variant = IconButtonVariant.PrimaryGhost) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "PrimaryGhost")
                }
            }
        }
    }
}

@Composable
@Preview("Secondary Icon Buttons")
fun SecondaryIconButtonPreview() {
    AppTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Secondary Icon Buttons", style = AppTheme.typography.h2)

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                IconButton(variant = IconButtonVariant.SecondaryFilled) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "SecondaryFilled")
                }
                IconButton(variant = IconButtonVariant.SecondaryOutlined) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "SecondaryOutlined")
                }
                IconButton(variant = IconButtonVariant.SecondaryElevated) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "SecondaryElevated")
                }
                IconButton(variant = IconButtonVariant.SecondaryGhost) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "SecondaryGhost")
                }
            }
        }
    }
}

@Composable
@Preview("Destructive Icon Buttons")
fun DestructiveIconButtonPreview() {
    AppTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Destructive Icon Buttons", style = AppTheme.typography.h2)

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                IconButton(variant = IconButtonVariant.DestructiveFilled) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "DestructiveFilled")
                }
                IconButton(variant = IconButtonVariant.DestructiveOutlined) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "DestructiveOutlined")
                }
                IconButton(variant = IconButtonVariant.DestructiveElevated) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "DestructiveElevated")
                }
                IconButton(variant = IconButtonVariant.DestructiveGhost) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "DestructiveGhost")
                }
            }
        }
    }
}

@Composable
@Preview("All Icon Button Shapes")
fun IconButtonShapesPreview() {
    AppTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Square Shape", style = AppTheme.typography.h2)

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                IconButton(
                    variant = IconButtonVariant.PrimaryFilled,
                    shape = IconButtonDefaults.ButtonSquareShape
                ) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "SquareShape")
                }
                IconButton(
                    variant = IconButtonVariant.PrimaryOutlined,
                    shape = IconButtonDefaults.ButtonSquareShape
                ) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "SquareShapeOutlined")
                }
            }

            Text(text = "Circle Shape", style = AppTheme.typography.h2)

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                IconButton(
                    variant = IconButtonVariant.PrimaryFilled,
                    shape = IconButtonDefaults.ButtonCircleShape
                ) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "CircleShape")
                }
                IconButton(
                    variant = IconButtonVariant.PrimaryOutlined,
                    shape = IconButtonDefaults.ButtonCircleShape
                ) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "CircleShapeOutlined")
                }
            }
        }
    }
}