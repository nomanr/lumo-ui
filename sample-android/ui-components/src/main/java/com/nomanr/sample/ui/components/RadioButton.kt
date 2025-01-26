package com.nomanr.sample.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.components.RadioButtonDefaults.MinimumInteractiveSize
import com.nomanr.sample.ui.components.RadioButtonDefaults.RadioAnimationDuration
import com.nomanr.sample.ui.components.RadioButtonDefaults.RadioButtonIconSize
import com.nomanr.sample.ui.components.RadioButtonDefaults.RadioButtonPadding
import com.nomanr.sample.ui.components.RadioButtonDefaults.RadioSelectedStrokeWidth
import com.nomanr.sample.ui.components.RadioButtonDefaults.RadioStrokeWidth
import com.nomanr.sample.ui.foundation.ripple

@Composable
fun RadioButton(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable (() -> Unit)? = null,
) {
    val strokeWidth =
        animateDpAsState(
            targetValue = if (selected) RadioSelectedStrokeWidth else RadioStrokeWidth,
            animationSpec = tween(durationMillis = RadioAnimationDuration),
            label = "RadioButtonStrokeWidth",
        )
    val radioColor = colors.radioColor(enabled, selected)
    val selectableModifier =
        if (onClick != null) {
            Modifier.selectable(
                selected = selected,
                onClick = onClick,
                enabled = enabled,
                role = Role.RadioButton,
                interactionSource = interactionSource,
                indication =
                    ripple(
                        bounded = false,
                        radius = MinimumInteractiveSize / 2,
                    ),
            )
        } else {
            Modifier
        }

    val clickableModifier =
        if (onClick != null && content != null) {
            Modifier.clickable(
                enabled = enabled,
                role = Role.RadioButton,
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            )
        } else {
            Modifier
        }

    Row(
        modifier = clickableModifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Canvas(
            modifier
                .then(
                    if (onClick != null) {
                        Modifier.requiredSize(MinimumInteractiveSize)
                    } else {
                        Modifier
                    },
                )
                .then(selectableModifier)
                .wrapContentSize(Alignment.Center)
                .padding(RadioButtonPadding)
                .requiredSize(RadioButtonIconSize),
        ) {
            drawCircle(
                radioColor.value,
                radius = (RadioButtonIconSize / 2).toPx() - strokeWidth.value.toPx() / 2,
                style = Stroke(strokeWidth.value.toPx()),
            )
        }
        if (content != null) {
            content()
        }
    }
}

object RadioButtonDefaults {
    const val RadioAnimationDuration = 100

    val RadioButtonPadding = 2.dp
    val RadioSelectedStrokeWidth = 6.dp
    val RadioStrokeWidth = 2.dp
    val RadioButtonIconSize = 20.dp
    val MinimumInteractiveSize = 44.dp

    @Composable
    fun colors() =
        RadioButtonColors(
            selectedColor = AppTheme.colors.primary,
            unselectedColor = AppTheme.colors.primary,
            disabledSelectedColor = AppTheme.colors.disabled,
            disabledUnselectedColor = AppTheme.colors.disabled,
        )
}

@Immutable
data class RadioButtonColors(
    val selectedColor: Color,
    val unselectedColor: Color,
    val disabledSelectedColor: Color,
    val disabledUnselectedColor: Color,
) {
    @Composable
    internal fun radioColor(enabled: Boolean, selected: Boolean): State<Color> {
        val target =
            when {
                enabled && selected -> selectedColor
                enabled && !selected -> unselectedColor
                !enabled && selected -> disabledSelectedColor
                else -> disabledUnselectedColor
            }

        return if (enabled) {
            animateColorAsState(target, tween(durationMillis = RadioAnimationDuration), label = "radioColor")
        } else {
            rememberUpdatedState(target)
        }
    }
}
