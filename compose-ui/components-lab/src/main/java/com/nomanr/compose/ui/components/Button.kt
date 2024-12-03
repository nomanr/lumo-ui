package com.nomanr.compose.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nomanr.compose.ui.AppTheme
import com.nomanr.compose.ui.foundation.ButtonElevation

@Composable
fun Button(
    modifier: Modifier = Modifier,
    text: String? = null,
    enabled: Boolean = true,
    loading: Boolean = false,
    variant: ButtonVariant = ButtonVariant.Primary,
    onClick: () -> Unit = {},
    contentPadding: PaddingValues = ButtonDefaults.contentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: (@Composable () -> Unit)? = null,
) {
    ButtonComponent(
        text = text,
        modifier = modifier,
        enabled = enabled,
        loading = loading,
        style = buttonStyleFor(variant),
        onClick = onClick,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content
    )
}

@Composable
internal fun ButtonComponent(
    text: String? = null,
    modifier: Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    style: ButtonStyle,
    onClick: () -> Unit,
    contentPadding: PaddingValues = ButtonDefaults.contentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: (@Composable () -> Unit)? = null,
) {

    val containerColor = style.colors.containerColor(enabled).value
    val contentColor = style.colors.contentColor(enabled).value
    val borderColor = style.colors.borderColor(enabled).value
    val borderStroke = if (borderColor != null) BorderStroke(
        ButtonDefaults.OutlineHeight, borderColor
    ) else null

    val shadowElevation = style.elevation?.shadowElevation(enabled, interactionSource)?.value ?: 0.dp

//    in case of full width button
//    val buttonModifier = modifier.fillMaxWidth()

    Surface(
        onClick = onClick,
        modifier = modifier
            .defaultMinSize(minHeight = ButtonDefaults.MinHeight)
            .semantics { role = Role.Button },
        enabled = enabled,
        shape = style.shape,
        color = containerColor,
        contentColor = contentColor,
        border = borderStroke,
        shadowElevation = shadowElevation,
        interactionSource = interactionSource
    ) {

        DefaultButtonContent(
            text = text,
            loading = loading,
            contentColor = contentColor,
            content = content,
            modifier = Modifier.padding(contentPadding)
        )
    }
}

@Composable
private fun DefaultButtonContent(
    modifier: Modifier = Modifier,
    text: String? = null,
    loading: Boolean,
    contentColor: Color,
    content: (@Composable () -> Unit)? = null
) {

    if (text?.isEmpty() == false) {
        Row(
            modifier, horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,

            ) {

//            if (!loading) {
//                CircularProgressIndicator(
//                    color = contentColor,
//                    modifier = Modifier.size(20.dp),
//                    strokeWidth = 2.dp
//                )
//            }

            Text(
                text = AnnotatedString(text = text),
                textAlign = TextAlign.Center,
                style = AppTheme.typography.button,
                overflow = TextOverflow.Clip,
                color = contentColor
            )
        }
    } else if (content != null) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center,
        ) {
            content()
        }
    }
}

enum class ButtonVariant {
    Primary, PrimaryOutlined, PrimaryElevated, PrimaryGhost, Secondary, SecondaryOutlined, SecondaryElevated, SecondaryGhost, Destructive, DestructiveOutlined, DestructiveElevated, DestructiveGhost
}

@Composable
internal fun buttonStyleFor(variant: ButtonVariant): ButtonStyle {
    return when (variant) {
        ButtonVariant.Primary -> ButtonDefaults.primaryFilled()
        ButtonVariant.PrimaryOutlined -> ButtonDefaults.primaryOutlined()
        ButtonVariant.PrimaryElevated -> ButtonDefaults.primaryElevated()
        ButtonVariant.PrimaryGhost -> ButtonDefaults.primaryGhost()
        ButtonVariant.Secondary -> ButtonDefaults.secondaryFilled()
        ButtonVariant.SecondaryOutlined -> ButtonDefaults.secondaryOutlined()
        ButtonVariant.SecondaryElevated -> ButtonDefaults.secondaryElevated()
        ButtonVariant.SecondaryGhost -> ButtonDefaults.secondaryGhost()
        ButtonVariant.Destructive -> ButtonDefaults.destructiveFilled()
        ButtonVariant.DestructiveOutlined -> ButtonDefaults.destructiveOutlined()
        ButtonVariant.DestructiveElevated -> ButtonDefaults.destructiveElevated()
        ButtonVariant.DestructiveGhost -> ButtonDefaults.destructiveGhost()
    }
}

internal object ButtonDefaults {
    internal val MinHeight = 44.dp
    internal val OutlineHeight = 1.dp
    private val ButtonHorizontalPadding = 16.dp
    private val ButtonVerticalPadding = 8.dp
    private val ButtonShape = RoundedCornerShape(12)


    val contentPadding = PaddingValues(
        start = ButtonHorizontalPadding,
        top = ButtonVerticalPadding,
        end = ButtonHorizontalPadding,
        bottom = ButtonVerticalPadding
    )

    private val filledShape = ButtonShape
    private val elevatedShape = ButtonShape
    private val outlinedShape = ButtonShape

    @Composable
    fun buttonElevation() = ButtonElevation(
        defaultElevation = 2.dp,
        pressedElevation = 2.dp,
        focusedElevation = 2.dp,
        hoveredElevation = 2.dp,
        disabledElevation = 0.dp
    )

    @Composable
    fun primaryFilled() = ButtonStyle(
        colors = ButtonColors(
            containerColor = AppTheme.colors.primary,
            contentColor = AppTheme.colors.onPrimary,
            disabledContainerColor = AppTheme.colors.disabled,
            disabledContentColor = AppTheme.colors.onDisabled,
        ),
        shape = filledShape,
        elevation = null,
        contentPadding = contentPadding,
    )

    @Composable
    fun primaryElevated() = ButtonStyle(
        colors = ButtonColors(
            containerColor = AppTheme.colors.primary,
            contentColor = AppTheme.colors.onPrimary,
            disabledContainerColor = AppTheme.colors.disabled,
            disabledContentColor = AppTheme.colors.onDisabled,
        ),
        shape = elevatedShape,
        elevation = buttonElevation(),
        contentPadding = contentPadding,
    )

    @Composable
    fun primaryOutlined() = ButtonStyle(
        colors = ButtonColors(
            containerColor = AppTheme.colors.transparent,
            contentColor = AppTheme.colors.primary,
            borderColor = AppTheme.colors.primary,
            disabledContainerColor = AppTheme.colors.transparent,
            disabledContentColor = AppTheme.colors.onDisabled,
            disabledBorderColor = AppTheme.colors.disabled,
        ),
        shape = outlinedShape,
        elevation = null,
        contentPadding = contentPadding,
    )

    @Composable
    fun primaryGhost() = ButtonStyle(
        colors = ButtonColors(
            containerColor = AppTheme.colors.transparent,
            contentColor = AppTheme.colors.primary,
            borderColor = AppTheme.colors.transparent,
            disabledContainerColor = AppTheme.colors.transparent,
            disabledContentColor = AppTheme.colors.onDisabled,
            disabledBorderColor = AppTheme.colors.transparent,
        ),
        shape = filledShape,
        elevation = null,
        contentPadding = contentPadding,
    )

    @Composable
    fun secondaryFilled() = ButtonStyle(
        colors = ButtonColors(
            containerColor = AppTheme.colors.secondary,
            contentColor = AppTheme.colors.onSecondary,
            disabledContainerColor = AppTheme.colors.disabled,
            disabledContentColor = AppTheme.colors.onDisabled,
        ),
        shape = filledShape,
        elevation = null,
        contentPadding = contentPadding,
    )

    @Composable
    fun secondaryElevated() = ButtonStyle(
        colors = ButtonColors(
            containerColor = AppTheme.colors.secondary,
            contentColor = AppTheme.colors.onSecondary,
            disabledContainerColor = AppTheme.colors.disabled,
            disabledContentColor = AppTheme.colors.onDisabled,
        ),
        shape = elevatedShape,
        elevation = buttonElevation(),
        contentPadding = contentPadding,
    )

    @Composable
    fun secondaryOutlined() = ButtonStyle(
        colors = ButtonColors(
            containerColor = AppTheme.colors.transparent,
            contentColor = AppTheme.colors.primary,
            borderColor = AppTheme.colors.secondary,
            disabledContainerColor = AppTheme.colors.transparent,
            disabledContentColor = AppTheme.colors.onDisabled,
            disabledBorderColor = AppTheme.colors.disabled,
        ),
        shape = outlinedShape,
        elevation = null,
        contentPadding = contentPadding,
    )

    @Composable
    fun secondaryGhost() = ButtonStyle(
        colors = ButtonColors(
            containerColor = AppTheme.colors.transparent,
            contentColor = AppTheme.colors.primary,
            borderColor = AppTheme.colors.transparent,
            disabledContainerColor = AppTheme.colors.transparent,
            disabledContentColor = AppTheme.colors.onDisabled,
            disabledBorderColor = AppTheme.colors.transparent,
        ),
        shape = filledShape,
        elevation = null,
        contentPadding = contentPadding,
    )

    @Composable
    fun destructiveFilled() = ButtonStyle(
        colors = ButtonColors(
            containerColor = AppTheme.colors.error,
            contentColor = AppTheme.colors.onError,
            disabledContainerColor = AppTheme.colors.disabled,
            disabledContentColor = AppTheme.colors.onDisabled,
        ),
        shape = filledShape,
        elevation = null,
        contentPadding = contentPadding,
    )

    @Composable
    fun destructiveElevated() = ButtonStyle(
        colors = ButtonColors(
            containerColor = AppTheme.colors.error,
            contentColor = AppTheme.colors.onError,
            disabledContainerColor = AppTheme.colors.disabled,
            disabledContentColor = AppTheme.colors.onDisabled,
        ),
        shape = elevatedShape,
        elevation = buttonElevation(),
        contentPadding = contentPadding,
    )

    @Composable
    fun destructiveOutlined() = ButtonStyle(
        colors = ButtonColors(
            containerColor = AppTheme.colors.transparent,
            contentColor = AppTheme.colors.error,
            borderColor = AppTheme.colors.error,
            disabledContainerColor = AppTheme.colors.transparent,
            disabledContentColor = AppTheme.colors.onDisabled,
            disabledBorderColor = AppTheme.colors.disabled,
        ),
        shape = outlinedShape,
        elevation = null,
        contentPadding = contentPadding,
    )

    @Composable
    fun destructiveGhost() = ButtonStyle(
        colors = ButtonColors(
            containerColor = AppTheme.colors.transparent,
            contentColor = AppTheme.colors.error,
            borderColor = AppTheme.colors.transparent,
            disabledContainerColor = AppTheme.colors.transparent,
            disabledContentColor = AppTheme.colors.onDisabled,
            disabledBorderColor = AppTheme.colors.transparent,
        ),
        shape = filledShape,
        elevation = null,
        contentPadding = contentPadding,
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
    fun borderColor(enabled: Boolean) = rememberUpdatedState(newValue = if (enabled) borderColor else disabledBorderColor)
}

@Immutable
internal data class ButtonStyle(
    val colors: ButtonColors,
    val shape: Shape,
    val elevation: ButtonElevation? = null,
    val contentPadding: PaddingValues,
)


@Composable
@Preview("Button", backgroundColor = 0xFFFFFFFF, showBackground = true)
fun ButtonPreview() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        PrimaryButtonPreview()
        SecondaryButtonPreview()
        DestructiveButtonPreview()
    }

}

@Composable
@Preview("Primary Buttons")
fun PrimaryButtonPreview() {
    AppTheme {
        Column(
            modifier = Modifier
                .background(AppTheme.colors.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Primary Buttons", style = AppTheme.typography.h2)

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(text = "PrimaryFilled", variant = ButtonVariant.Primary, onClick = {})

                Button(
                    text = "Disabled", variant = ButtonVariant.Primary, enabled = false
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(text = "PrimaryOutlined", variant = ButtonVariant.PrimaryOutlined, onClick = {})

                Button(
                    text = "Disabled", variant = ButtonVariant.PrimaryOutlined, enabled = false
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(text = "PrimaryElevated", variant = ButtonVariant.PrimaryElevated, onClick = {})

                Button(
                    text = "Disabled", variant = ButtonVariant.PrimaryElevated, enabled = false
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(text = "PrimaryGhost", variant = ButtonVariant.PrimaryGhost, onClick = {})

                Button(
                    text = "Disabled", variant = ButtonVariant.PrimaryGhost, enabled = false
                )
            }
        }
    }
}

@Composable
@Preview("Secondary Buttons")
fun SecondaryButtonPreview() {
    AppTheme {
        Column(
            modifier = Modifier
                .background(AppTheme.colors.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Secondary Buttons", style = AppTheme.typography.h2)

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(text = "SecondaryFilled", variant = ButtonVariant.Secondary, onClick = {})

                Button(
                    text = "Disabled", variant = ButtonVariant.Secondary, enabled = false
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(text = "SecondaryOutlined", variant = ButtonVariant.SecondaryOutlined, onClick = {})

                Button(
                    text = "Disabled", variant = ButtonVariant.SecondaryOutlined, enabled = false
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(text = "SecondaryElevated", variant = ButtonVariant.SecondaryElevated, onClick = {})

                Button(
                    text = "Disabled", variant = ButtonVariant.SecondaryElevated, enabled = false
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(text = "SecondaryGhost", variant = ButtonVariant.SecondaryGhost, onClick = {})

                Button(
                    text = "Disabled", variant = ButtonVariant.SecondaryGhost, enabled = false
                )
            }
        }
    }
}

@Composable
@Preview("Destructive Buttons")
fun DestructiveButtonPreview() {
    AppTheme {
        Column(
            modifier = Modifier
                .background(AppTheme.colors.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Destructive Buttons", style = AppTheme.typography.h2)

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(text = "DestructiveFilled", variant = ButtonVariant.Destructive, onClick = {})

                Button(
                    text = "Disabled", variant = ButtonVariant.Destructive, enabled = false
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(text = "DestructiveOutlined", variant = ButtonVariant.DestructiveOutlined, onClick = {})

                Button(
                    text = "Disabled", variant = ButtonVariant.DestructiveOutlined, enabled = false
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(text = "DestructiveElevated", variant = ButtonVariant.DestructiveElevated, onClick = {})

                Button(
                    text = "Disabled", variant = ButtonVariant.DestructiveElevated, enabled = false
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(text = "DestructiveGhost", variant = ButtonVariant.DestructiveGhost, onClick = {})

                Button(
                    text = "Disabled", variant = ButtonVariant.DestructiveGhost, enabled = false
                )
            }
        }
    }
}
