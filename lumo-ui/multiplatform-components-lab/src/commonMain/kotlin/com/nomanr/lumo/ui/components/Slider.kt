package com.nomanr.lumo.ui.components

import androidx.annotation.IntRange
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nomanr.composables.slider.BasicRangeSlider
import com.nomanr.composables.slider.BasicSlider
import com.nomanr.composables.slider.RangeSliderState
import com.nomanr.composables.slider.SliderColors
import com.nomanr.composables.slider.SliderState
import com.nomanr.lumo.ui.AppTheme

@Composable
fun Slider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onValueChangeFinished: (() -> Unit)? = null,
    colors: SliderColors = SliderDefaults.colors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    @IntRange(from = 0) steps: Int = 0,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f
) {
    val state = remember(steps, valueRange) {
        SliderState(
            value,
            steps,
            onValueChangeFinished,
            valueRange
        )
    }

    state.onValueChangeFinished = onValueChangeFinished
    state.onValueChange = onValueChange
    state.value = value

    Slider(
        state = state, modifier = modifier, enabled = enabled, interactionSource = interactionSource, colors = colors
    )
}

@Composable
fun Slider(
    state: SliderState,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: SliderColors = SliderDefaults.colors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    require(state.steps >= 0) { "steps should be >= 0" }

    BasicSlider(modifier = modifier, state = state, colors = colors, enabled = enabled, interactionSource = interactionSource)
}

@Composable
fun RangeSlider(
    value: ClosedFloatingPointRange<Float>,
    onValueChange: (ClosedFloatingPointRange<Float>) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    @IntRange(from = 0) steps: Int = 0,
    onValueChangeFinished: (() -> Unit)? = null,
    colors: SliderColors = SliderDefaults.colors(),
    startInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    endInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {

    val state =
        remember(steps, valueRange) {
            RangeSliderState(
                value.start,
                value.endInclusive,
                steps,
                onValueChangeFinished,
                valueRange
            )
        }

    state.onValueChangeFinished = onValueChangeFinished
    state.onValueChange = { onValueChange(it.start..it.endInclusive) }
    state.activeRangeStart = value.start
    state.activeRangeEnd = value.endInclusive

    RangeSlider(
        state = state,
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        startInteractionSource = startInteractionSource,
        endInteractionSource = endInteractionSource
    )
}

@Composable
fun RangeSlider(
    state: RangeSliderState,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: SliderColors = SliderDefaults.colors(),
    startInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    endInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    require(state.steps >= 0) { "steps should be >= 0" }

    BasicRangeSlider(
        modifier = modifier,
        state = state,
        enabled = enabled,
        startInteractionSource = startInteractionSource,
        endInteractionSource = endInteractionSource,
        colors = colors
    )
}

@Stable
object SliderDefaults {

    @Composable
    fun colors(
        thumbColor: Color = AppTheme.colors.primary,
        activeTrackColor: Color = AppTheme.colors.primary,
        activeTickColor: Color = AppTheme.colors.onPrimary,
        inactiveTrackColor: Color = AppTheme.colors.secondary,
        inactiveTickColor: Color = AppTheme.colors.primary,
        disabledThumbColor: Color = AppTheme.colors.disabled,
        disabledActiveTrackColor: Color = AppTheme.colors.disabled,
        disabledActiveTickColor: Color = AppTheme.colors.disabled,
        disabledInactiveTrackColor: Color = AppTheme.colors.disabled,
        disabledInactiveTickColor: Color = Color.Unspecified
    ) = SliderColors(
        thumbColor = thumbColor,
        activeTrackColor = activeTrackColor,
        activeTickColor = activeTickColor,
        inactiveTrackColor = inactiveTrackColor,
        inactiveTickColor = inactiveTickColor,
        disabledThumbColor = disabledThumbColor,
        disabledActiveTrackColor = disabledActiveTrackColor,
        disabledActiveTickColor = disabledActiveTickColor,
        disabledInactiveTrackColor = disabledInactiveTrackColor,
        disabledInactiveTickColor = disabledInactiveTickColor
    )
}

@Preview(
    name = "Slider Preview", showBackground = true, backgroundColor = 0xFFFFFFFF, showSystemUi = true
)
@Composable
private fun SliderPreview() {
    AppTheme {
        Column(
            modifier = Modifier
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Text(
                text = "Slider Components", style = AppTheme.typography.h3
            )

            Column {
                Text(
                    text = "Basic Slider", style = AppTheme.typography.h4, color = AppTheme.colors.primary
                )
                var value by remember { mutableFloatStateOf(0.5f) }
                Slider(
                    value = value,
                    onValueChange = { value = it },
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            Column {
                Text(
                    text = "Stepped Slider (5 steps)", style = AppTheme.typography.h4, color = AppTheme.colors.primary
                )
                var value by remember { mutableFloatStateOf(0.4f) }
                Slider(
                    value = value,
                    onValueChange = { value = it },
                    steps = 4,
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            Column {
                Text(
                    text = "Custom Range (0-100)", style = AppTheme.typography.h4, color = AppTheme.colors.primary
                )
                var value by remember { mutableFloatStateOf(30f) }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Slider(
                        value = value,
                        onValueChange = { value = it },
                        valueRange = 0f..100f,
                        modifier = Modifier.weight(1f),
                    )
                    Text(
                        text = "${value.toInt()}", style = AppTheme.typography.body1, modifier = Modifier.width(40.dp)
                    )
                }
            }

            Column {
                Text(
                    text = "Disabled States", style = AppTheme.typography.h4, color = AppTheme.colors.primary
                )
                Slider(
                    value = 0.3f, onValueChange = {}, enabled = false, modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                Slider(
                    value = 0.7f, onValueChange = {}, enabled = false, modifier = Modifier.fillMaxWidth()

                )
            }

            Column {
                Text(
                    text = "Custom Colors", style = AppTheme.typography.h4, color = AppTheme.colors.primary
                )
                var value by remember { mutableFloatStateOf(0.5f) }
                Slider(
                    value = value, onValueChange = { value = it },
                    colors = SliderDefaults.colors(
                        thumbColor = AppTheme.colors.error,
                        activeTrackColor = AppTheme.colors.error,
                        inactiveTrackColor = AppTheme.colors.error.copy(alpha = 0.3f)
                    ),
                    modifier = Modifier.fillMaxWidth()

                )
            }

            Column {
                Text(
                    text = "Interactive Slider", style = AppTheme.typography.h4, color = AppTheme.colors.primary
                )
                var value by remember { mutableFloatStateOf(50f) }
                var isEditing by remember { mutableStateOf(false) }
                Text(
                    text = if (isEditing) "Editing..." else "Value: ${value.toInt()}", style = AppTheme.typography.body1
                )
                Slider(
                    value = value,
                    onValueChange = {
                        value = it
                        isEditing = true
                    },
                    valueRange = 0f..100f, onValueChangeFinished = { isEditing = false }, modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(
    name = "Range Slider Preview", showBackground = true, backgroundColor = 0xFFFFFFFF, showSystemUi = true
)
@Composable
private fun RangeSliderPreview() {
    AppTheme {
        Column(
            modifier = Modifier
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Text(
                text = "Range Slider Components", style = AppTheme.typography.h3
            )

            Column {
                Text(
                    text = "Basic Range Slider", style = AppTheme.typography.h4, color = AppTheme.colors.primary
                )
                var range by remember { mutableStateOf(0.2f..0.8f) }
                RangeSlider(
                    value = range, onValueChange = { range = it }, modifier = Modifier.fillMaxWidth()
                )
            }

            Column {
                Text(
                    text = "Stepped Range Slider (5 steps)", style = AppTheme.typography.h4, color = AppTheme.colors.primary
                )
                var range by remember { mutableStateOf(0.2f..0.6f) }
                RangeSlider(
                    value = range, onValueChange = { range = it }, steps = 4, modifier = Modifier.fillMaxWidth()
                )
            }

            Column {
                Text(
                    text = "Custom Range (0-100)", style = AppTheme.typography.h4, color = AppTheme.colors.primary
                )
                var range by remember { mutableStateOf(20f..80f) }
                Column {
                    RangeSlider(
                        value = range, onValueChange = { range = it }, valueRange = 0f..100f, modifier = Modifier.fillMaxWidth()
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Start: ${range.start.toInt()}", style = AppTheme.typography.body1
                        )
                        Text(
                            text = "End: ${range.endInclusive.toInt()}", style = AppTheme.typography.body1
                        )
                    }
                }
            }

            Column {
                Text(
                    text = "Disabled State", style = AppTheme.typography.h4, color = AppTheme.colors.primary
                )
                RangeSlider(
                    value = 0.3f..0.7f, onValueChange = {}, enabled = false, modifier = Modifier.fillMaxWidth()
                )
            }

            Column {
                Text(
                    text = "Custom Colors", style = AppTheme.typography.h4, color = AppTheme.colors.primary
                )
                var range by remember { mutableStateOf(0.3f..0.7f) }
                RangeSlider(
                    value = range, onValueChange = { range = it },
                    colors = SliderDefaults.colors(
                        thumbColor = AppTheme.colors.error,
                        activeTrackColor = AppTheme.colors.error,
                        inactiveTrackColor = AppTheme.colors.error.copy(alpha = 0.3f)
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Column {
                Text(
                    text = "Interactive Range Slider", style = AppTheme.typography.h4, color = AppTheme.colors.primary
                )
                var range by remember { mutableStateOf(30f..70f) }
                var isEditing by remember { mutableStateOf(false) }
                Text(
                    text = if (isEditing) "Editing..." else "Range: ${range.start.toInt()} - ${range.endInclusive.toInt()}",
                    style = AppTheme.typography.body1
                )
                RangeSlider(
                    value = range,
                    onValueChange = {
                        range = it
                        isEditing = true
                    },
                    valueRange = 0f..100f, onValueChangeFinished = { isEditing = false }, modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
