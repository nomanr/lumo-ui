package com.nomanr.sample.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.toggleable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.LocalContentColor
import com.nomanr.sample.ui.contentColorFor
import com.nomanr.sample.ui.foundation.ripple

@Composable
@NonRestartableComposable
fun Surface(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    color: Color = AppTheme.colors.surface,
    contentColor: Color = contentColorFor(color),
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = null,
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(
        LocalContentColor provides contentColor
    ) {
        Box(modifier = modifier
            .surface(
                shape = shape, backgroundColor = color, border = border, shadowElevation = shadowElevation
            )
            .semantics(mergeDescendants = false) {
                isTraversalGroup = true
            }
            .pointerInput(Unit) {}, propagateMinConstraints = true
        ) {
            content()
        }
    }
}

@Composable
@NonRestartableComposable
fun Surface(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = RectangleShape,
    color: Color = AppTheme.colors.background,
    contentColor: Color = contentColorFor(color),
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(
        LocalContentColor provides contentColor
    ) {
        Box(
            modifier = modifier
                .surface(
                    shape = shape, backgroundColor = color, border = border, shadowElevation = shadowElevation
                )
                .clickable(
                    interactionSource = interactionSource,
                    indication = ripple(color = contentColor),
                    enabled = enabled,
                    onClick = onClick
                ), propagateMinConstraints = true
        ) {
            content()
        }
    }
}

@Composable
@NonRestartableComposable
fun Surface(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = RectangleShape,
    color: Color = AppTheme.colors.background,
    contentColor: Color = contentColorFor(color),
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(
        LocalContentColor provides contentColor
    ) {
        Box(
            modifier = modifier
                .surface(
                    shape = shape, backgroundColor = color, border = border, shadowElevation = shadowElevation
                )
                .selectable(
                    selected = selected,
                    interactionSource = interactionSource,
                    indication = ripple(),
                    enabled = enabled,
                    onClick = onClick
                ), propagateMinConstraints = true
        ) {
            content()
        }
    }
}

@Composable
@NonRestartableComposable
fun Surface(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = RectangleShape,
    color: Color = AppTheme.colors.background,
    contentColor: Color = contentColorFor(color),
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(
        LocalContentColor provides contentColor
    ) {
        Box(
            modifier = modifier
                .surface(
                    shape = shape, backgroundColor = color, border = border, shadowElevation = shadowElevation
                )
                .toggleable(
                    value = checked,
                    interactionSource = interactionSource,
                    indication = ripple(),
                    enabled = enabled,
                    onValueChange = onCheckedChange
                ), propagateMinConstraints = true
        ) {
            content()
        }
    }
}

private fun Modifier.surface(
    shape: Shape, backgroundColor: Color, border: BorderStroke?, shadowElevation: Dp
) = this
    .shadow(shadowElevation, shape, clip = false)
    .then(if (border != null) Modifier.border(border, shape) else Modifier)
    .background(color = backgroundColor, shape = shape)
    .clip(shape)
