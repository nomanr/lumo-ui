package com.nomanr.lumo.multiplatform.sample.sample.samples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nomanr.composables.slider.SliderColors
import com.nomanr.lumo.multiplatform.ui.AppTheme
import com.nomanr.lumo.multiplatform.ui.components.Checkbox
import com.nomanr.lumo.multiplatform.ui.components.RangeSlider
import com.nomanr.lumo.multiplatform.ui.components.Slider
import com.nomanr.lumo.multiplatform.ui.components.SliderDefaults
import com.nomanr.lumo.multiplatform.ui.components.Text
import kotlin.math.round

@Composable
fun SliderSample() {
    Column(
        modifier =
            Modifier
                .background(AppTheme.colors.background)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        SliderShowcase()
        Spacer(modifier = Modifier.height(24.dp))
        RangeSliderShowcase()
    }
}

@Composable
private fun SliderShowcase() {
    var slider1 by remember { mutableFloatStateOf(1f) }
    var slider2 by remember { mutableFloatStateOf(1f) }
    var slider3 by remember { mutableFloatStateOf(1f) }
    var slider4 by remember { mutableFloatStateOf(0.3f) }
    var enabled by remember { mutableStateOf(true) }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Enabled", style = AppTheme.typography.body1)
            Checkbox(checked = enabled, onCheckedChange = { enabled = it })
        }

        SliderWithLabel(
            title = "Basic Slider (0-1)",
            value = slider1,
            onValueChange = { slider1 = it },
            valueRange = 0f..1f,
            enabled = enabled,
        )

        SliderWithLabel(
            title = "Custom Range (1-10)",
            value = slider2,
            onValueChange = { slider2 = it },
            valueRange = 1f..10f,
            enabled = enabled,
        )

        SliderWithLabel(
            title = "Custom Range (1-10) with 1 step",
            value = slider3,
            onValueChange = { slider3 = it },
            valueRange = 1f..10f,
            steps = 8,
            enabled = enabled,
        )

        SliderWithLabel(
            title = "Custom Colors",
            value = slider4,
            onValueChange = { slider4 = it },
            valueRange = 0f..1f,
            enabled = enabled,
            colors =
                SliderDefaults.colors(
                    thumbColor = AppTheme.colors.error,
                    activeTrackColor = AppTheme.colors.error,
                    inactiveTrackColor = AppTheme.colors.error.copy(alpha = 0.3f),
                ),
        )
    }
}

@Composable
private fun SliderWithLabel(
    title: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    steps: Int = 0,
    enabled: Boolean,
    colors: SliderColors = SliderDefaults.colors(),
) {
    Column {
        Text(text = title, style = AppTheme.typography.h4, color = AppTheme.colors.primary)
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
            steps = steps,
            enabled = enabled,
            colors = colors,
            modifier = Modifier.fillMaxWidth(),
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            Text(
                text = "${round(value * 100.0) / 100.0}",
                style = AppTheme.typography.label3,
            )
        }
    }
}

@Composable
private fun RangeSliderShowcase() {
    var basicRange by remember { mutableStateOf(0.2f..0.8f) }
    var steppedRange by remember { mutableStateOf(0.2f..0.6f) }
    var customRange by remember { mutableStateOf(20f..80f) }
    var customRangeSliderState by remember { mutableStateOf(0.3f..0.7f) }
    var interactiveRange by remember { mutableStateOf(30f..70f) }
    var isEditing by remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        RangeSliderWithLabel(
            title = "Basic Range Slider",
            range = basicRange,
            onValueChange = { basicRange = it },
        )

        RangeSliderWithLabel(
            title = "Stepped Range Slider (5 steps)",
            range = steppedRange,
            steps = 4,
            onValueChange = { steppedRange = it },
        )

        RangeSliderWithLabel(
            title = "Custom Range (0-100)",
            range = customRange,
            onValueChange = { customRange = it },
            valueRange = 0f..100f,
        )

        RangeSliderWithLabel(
            title = "Disabled State",
            range = 0.3f..0.7f,
            onValueChange = {},
            enabled = false,
        )

        RangeSliderWithLabel(
            title = "Custom Colors",
            range = customRangeSliderState,
            onValueChange = { customRangeSliderState = it },
            colors =
                SliderDefaults.colors(
                    thumbColor = AppTheme.colors.error,
                    activeTrackColor = AppTheme.colors.error,
                    inactiveTrackColor = AppTheme.colors.error.copy(alpha = 0.3f),
                ),
        )

        RangeSliderWithLabel(
            title = "Interactive Range Slider",
            range = interactiveRange,
            onValueChange = {
                interactiveRange = it
                isEditing = true
            },
            onValueChangeFinished = { isEditing = false },
            valueRange = 0f..100f,
        )
    }
}

@Composable
private fun RangeSliderWithLabel(
    title: String,
    range: ClosedFloatingPointRange<Float>,
    onValueChange: (ClosedFloatingPointRange<Float>) -> Unit,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    enabled: Boolean = true,
    colors: SliderColors = SliderDefaults.colors(),
    onValueChangeFinished: (() -> Unit)? = null,
) {
    Column {
        Text(text = title, style = AppTheme.typography.h4, color = AppTheme.colors.primary)
        RangeSlider(
            value = range,
            onValueChange = onValueChange,
            valueRange = valueRange,
            steps = steps,
            enabled = enabled,
            colors = colors,
            onValueChangeFinished = onValueChangeFinished ?: {},
            modifier = Modifier.fillMaxWidth(),
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Start: ${round(range.start * 100.0) / 100.0}",
                style = AppTheme.typography.label3,
            )
            Text(
                text = "End: ${round(range.endInclusive * 100.0) / 100.0}",
                style = AppTheme.typography.label3,
            )
        }
    }
}
