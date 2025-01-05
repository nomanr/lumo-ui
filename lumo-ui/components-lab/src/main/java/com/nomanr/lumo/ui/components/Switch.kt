package com.nomanr.lumo.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.nomanr.lumo.ui.AppTheme
import com.nomanr.lumo.ui.LocalContentColor
import com.nomanr.lumo.ui.components.SwitchDefaults.RippleRadius
import com.nomanr.lumo.ui.components.SwitchDefaults.SwitchHeight
import com.nomanr.lumo.ui.components.SwitchDefaults.SwitchWidth
import com.nomanr.lumo.ui.components.SwitchDefaults.ThumbSize
import com.nomanr.lumo.ui.components.SwitchDefaults.ThumbSizeStateOffset
import com.nomanr.lumo.ui.components.SwitchDefaults.TrackBorderWidth
import com.nomanr.lumo.ui.components.SwitchDefaults.TrackShape
import com.nomanr.lumo.ui.components.SwitchDefaults.UncheckedThumbSize
import com.nomanr.lumo.ui.foundation.ripple
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun Switch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    thumbContent: (@Composable () -> Unit)? = null,
    enabled: Boolean = true,
    colors: SwitchColors = SwitchDefaults.colors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val scope = rememberCoroutineScope()
    val pressed by interactionSource.collectIsPressedAsState()

    val animationState = remember {
        SwitchAnimationState(checked, pressed)
    }

    LaunchedEffect(checked, pressed) {
        animationState.animateTo(checked, pressed, scope)
    }

    val toggleableModifier = if (onCheckedChange != null) {
        Modifier.toggleable(
            value = checked,
            onValueChange = onCheckedChange,
            enabled = enabled,
            role = Role.Switch,
            interactionSource = interactionSource,
            indication = null
        )
    } else {
        Modifier
    }

    SwitchComponent(
        modifier = modifier.then(toggleableModifier),
        checked = checked,
        enabled = enabled,
        colors = colors,
        interactionSource = interactionSource,
        thumbContent = thumbContent,
        thumbPosition = animationState.thumbPosition.value,
        thumbSizeOffset = animationState.thumbSizeOffset.value
    )
}

@Composable
private fun SwitchComponent(
    modifier: Modifier,
    checked: Boolean,
    enabled: Boolean,
    colors: SwitchColors,
    interactionSource: InteractionSource,
    thumbContent: (@Composable () -> Unit)?,
    thumbPosition: Float,
    thumbSizeOffset: Float
) {
    val borderColor = colors.borderColor(enabled = enabled, checked = checked)

    Box(
        modifier = modifier
            .size(SwitchWidth, SwitchHeight)
            .background(
                color = colors.trackColor(enabled, checked),
                shape = TrackShape
            )
            .border(
                width = TrackBorderWidth,
                color = borderColor,
                shape = TrackShape
            )
    ) {

        val checkedThumbSize = UncheckedThumbSize + ThumbSizeStateOffset * thumbPosition
        val uncheckedThumbSize =
            UncheckedThumbSize + ThumbSizeStateOffset * if (thumbPosition == 0f) thumbSizeOffset else thumbPosition

        val thumbSize = if (checked) checkedThumbSize else uncheckedThumbSize
        val verticalPadding = (SwitchHeight - ThumbSize) / 2

        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(thumbSize)
                .offset {
                    val trackWidth = SwitchWidth.toPx()
                    val currentThumbSize = thumbSize.toPx()
                    val maxThumbSize = ThumbSize.toPx()
                    val padding = verticalPadding.toPx()

                    val totalMovableDistance = trackWidth - maxThumbSize - (padding * 2)
                    val sizeDifference = (maxThumbSize - currentThumbSize) / 2

                    IntOffset(
                        x = (padding + sizeDifference + (totalMovableDistance * thumbPosition)).roundToInt(),
                        y = 0
                    )
                }
                .drawBehind {
                    drawCircle(
                        color = colors.thumbColor(enabled, checked)
                    )
                }
                .indication(
                    interactionSource = interactionSource,
                    indication = ripple(
                        bounded = false,
                        radius = RippleRadius
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            if (thumbContent != null) {
                CompositionLocalProvider(
                    LocalContentColor provides colors.iconColor(enabled, checked)
                ) {
                    thumbContent()
                }
            }
        }
    }
}

object SwitchDefaults {
    val ThumbSize = 16.dp
    val UncheckedThumbSize = 12.dp
    val ThumbSizeStateOffset = ThumbSize - UncheckedThumbSize
    val SwitchWidth = 40.dp
    val SwitchHeight = 24.dp
    val TrackBorderWidth = 2.dp
    val TrackShape = RoundedCornerShape(50)
    val RippleRadius = 20.dp

    @Composable
    fun colors(
        checkedThumbColor: Color = AppTheme.colors.onPrimary,
        checkedTrackColor: Color = AppTheme.colors.primary,
        checkedBorderColor: Color = AppTheme.colors.primary,
        checkedIconColor: Color = AppTheme.colors.primary,
        uncheckedThumbColor: Color = AppTheme.colors.primary,
        uncheckedTrackColor: Color = AppTheme.colors.background,
        uncheckedBorderColor: Color = AppTheme.colors.primary,
        uncheckedIconColor: Color = AppTheme.colors.onPrimary,
        disabledCheckedThumbColor: Color = AppTheme.colors.onDisabled,
        disabledCheckedTrackColor: Color = AppTheme.colors.disabled,
        disabledCheckedBorderColor: Color = AppTheme.colors.disabled,
        disabledCheckedIconColor: Color = AppTheme.colors.disabled,
        disabledUncheckedThumbColor: Color = AppTheme.colors.disabled,
        disabledUncheckedTrackColor: Color = AppTheme.colors.transparent,
        disabledUncheckedBorderColor: Color = AppTheme.colors.disabled,
        disabledUncheckedIconColor: Color = AppTheme.colors.onDisabled
    ): SwitchColors = SwitchColors(
        checkedThumbColor = checkedThumbColor,
        checkedTrackColor = checkedTrackColor,
        checkedBorderColor = checkedBorderColor,
        checkedIconColor = checkedIconColor,
        uncheckedThumbColor = uncheckedThumbColor,
        uncheckedTrackColor = uncheckedTrackColor,
        uncheckedBorderColor = uncheckedBorderColor,
        uncheckedIconColor = uncheckedIconColor,
        disabledCheckedThumbColor = disabledCheckedThumbColor,
        disabledCheckedTrackColor = disabledCheckedTrackColor,
        disabledCheckedBorderColor = disabledCheckedBorderColor,
        disabledCheckedIconColor = disabledCheckedIconColor,
        disabledUncheckedThumbColor = disabledUncheckedThumbColor,
        disabledUncheckedTrackColor = disabledUncheckedTrackColor,
        disabledUncheckedBorderColor = disabledUncheckedBorderColor,
        disabledUncheckedIconColor = disabledUncheckedIconColor
    )
}

@Stable
class SwitchColors(
    private val checkedThumbColor: Color,
    private val checkedTrackColor: Color,
    private val checkedBorderColor: Color,
    private val checkedIconColor: Color,
    private val uncheckedThumbColor: Color,
    private val uncheckedTrackColor: Color,
    private val uncheckedBorderColor: Color,
    private val uncheckedIconColor: Color,
    private val disabledCheckedThumbColor: Color,
    private val disabledCheckedTrackColor: Color,
    private val disabledCheckedBorderColor: Color,
    private val disabledCheckedIconColor: Color,
    private val disabledUncheckedThumbColor: Color,
    private val disabledUncheckedTrackColor: Color,
    private val disabledUncheckedBorderColor: Color,
    private val disabledUncheckedIconColor: Color
) {
    @Stable
    internal fun thumbColor(enabled: Boolean, checked: Boolean): Color =
        when {
            enabled && checked -> checkedThumbColor
            enabled && !checked -> uncheckedThumbColor
            !enabled && checked -> disabledCheckedThumbColor
            else -> disabledUncheckedThumbColor
        }

    @Stable
    internal fun trackColor(enabled: Boolean, checked: Boolean): Color =
        when {
            enabled && checked -> checkedTrackColor
            enabled && !checked -> uncheckedTrackColor
            !enabled && checked -> disabledCheckedTrackColor
            else -> disabledUncheckedTrackColor
        }

    @Stable
    internal fun borderColor(enabled: Boolean, checked: Boolean): Color =
        when {
            enabled && checked -> checkedBorderColor
            enabled && !checked -> uncheckedBorderColor
            !enabled && checked -> disabledCheckedBorderColor
            else -> disabledUncheckedBorderColor
        }

    @Stable
    internal fun iconColor(enabled: Boolean, checked: Boolean): Color =
        when {
            enabled && checked -> checkedIconColor
            enabled && !checked -> uncheckedIconColor
            !enabled && checked -> disabledCheckedIconColor
            else -> disabledUncheckedIconColor
        }
}

@Stable
private class SwitchAnimationState(
    initialChecked: Boolean,
    initialPressed: Boolean
) {
    var checked by mutableStateOf(initialChecked)
    var pressed by mutableStateOf(initialPressed)

    val thumbPosition = Animatable(if (checked) 1f else 0f)
    val thumbSizeOffset = Animatable(0f)

    val animationSpec = tween<Float>(
        durationMillis = 100,
        easing = FastOutSlowInEasing
    )

    suspend fun animateTo(
        targetChecked: Boolean,
        targetPressed: Boolean,
        scope: CoroutineScope
    ) {
        checked = targetChecked
        pressed = targetPressed

        scope.launch {
            thumbPosition.animateTo(
                targetValue = if (targetChecked) 1f else 0f,
                animationSpec = animationSpec
            )
        }
        scope.launch {
            thumbSizeOffset.animateTo(
                targetValue = if (targetPressed) 1f else 0f,
                animationSpec = animationSpec
            )
        }
    }
}


@Preview("Switch")
@Composable
private fun SwitchPreview() {
    AppTheme {
        Column(modifier = Modifier.padding(16.dp)) {

            val value = remember {
                mutableStateOf(false)
            }

            Spacer(modifier = Modifier.size(16.dp))
            Switch(
                checked = value.value,
                onCheckedChange = {
                    value.value = it
                }
            )
            Spacer(modifier = Modifier.size(16.dp))
            Switch(
                checked = value.value,
                onCheckedChange = {
                    value.value = it
                }
            )
            Spacer(modifier = Modifier.size(16.dp))

            Switch(
                checked = true,
                onCheckedChange = {
                    value.value = it
                }
            )
            Spacer(modifier = Modifier.size(16.dp))

            Switch(
                checked = false,
                onCheckedChange = {
                    value.value = it
                }
            )
            Spacer(modifier = Modifier.size(16.dp))

            Switch(
                checked = true,
                enabled = false,
                onCheckedChange = {
                    value.value = it
                }
            )
            Spacer(modifier = Modifier.size(16.dp))

            Switch(
                checked = false,
                enabled = false,
                onCheckedChange = {
                    value.value = it
                }
            )
            Spacer(modifier = Modifier.size(16.dp))

        }
    }
}
