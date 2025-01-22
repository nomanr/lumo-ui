package com.nomanr.lumo.ui.components.progress_indicators

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.progressSemantics
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.nomanr.lumo.ui.AppTheme
import com.nomanr.lumo.ui.components.Text
import kotlin.math.abs

@Composable
fun LinearProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = LinearProgressIndicatorDefaults.Color,
    trackColor: Color = LinearProgressIndicatorDefaults.TrackColor,
    strokeCap: StrokeCap = LinearProgressIndicatorDefaults.StrokeStyle,
) {
    val coercedProgress = progress.coerceIn(0f, 1f)
    Canvas(
        modifier
            .progressSemantics(coercedProgress)
            .height(LinearProgressIndicatorDefaults.TrackHeight)
            .fillMaxWidth()
    ) {
        val strokeWidth = size.height
        drawLinearIndicatorTrack(trackColor, strokeWidth, strokeCap)
        drawLinearIndicator(0f, coercedProgress, color, strokeWidth, strokeCap)
    }
}

@Composable
fun LinearProgressIndicator(
    modifier: Modifier = Modifier,
    color: Color = LinearProgressIndicatorDefaults.Color,
    trackColor: Color = LinearProgressIndicatorDefaults.TrackColor,
    strokeCap: StrokeCap = LinearProgressIndicatorDefaults.StrokeStyle,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "InfiniteTransition")
    val firstLineHead = infiniteTransition.animateFloat(0f, 1f, infiniteRepeatable(animation = keyframes {
        durationMillis = LinearProgressIndicatorDefaults.AnimationDuration
        0f at LinearProgressIndicatorDefaults.FirstLineHeadDelay using LinearProgressIndicatorDefaults.FirstLineHeadEasing
        1f at LinearProgressIndicatorDefaults.FirstLineHeadDuration + LinearProgressIndicatorDefaults.FirstLineHeadDelay
    }), label = "FirstLineHead")
    val firstLineTail = infiniteTransition.animateFloat(0f, 1f, infiniteRepeatable(animation = keyframes {
        durationMillis = LinearProgressIndicatorDefaults.AnimationDuration
        0f at LinearProgressIndicatorDefaults.FirstLineTailDelay using LinearProgressIndicatorDefaults.FirstLineTailEasing
        1f at LinearProgressIndicatorDefaults.FirstLineTailDuration + LinearProgressIndicatorDefaults.FirstLineTailDelay
    }), label = "FirstLineTail")
    val secondLineHead = infiniteTransition.animateFloat(0f, 1f, infiniteRepeatable(animation = keyframes {
        durationMillis = LinearProgressIndicatorDefaults.AnimationDuration
        0f at LinearProgressIndicatorDefaults.SecondLineHeadDelay using LinearProgressIndicatorDefaults.SecondLineHeadEasing
        1f at LinearProgressIndicatorDefaults.SecondLineHeadDuration + LinearProgressIndicatorDefaults.SecondLineHeadDelay
    }), label = "SecondLineHead")
    val secondLineTail = infiniteTransition.animateFloat(0f, 1f, infiniteRepeatable(animation = keyframes {
        durationMillis = LinearProgressIndicatorDefaults.AnimationDuration
        0f at LinearProgressIndicatorDefaults.SecondLineTailDelay using LinearProgressIndicatorDefaults.SecondLineTailEasing
        1f at LinearProgressIndicatorDefaults.SecondLineTailDuration + LinearProgressIndicatorDefaults.SecondLineTailDelay
    }), label = "SecondLineTail")
    Canvas(
        modifier
            .progressSemantics()
            .height(LinearProgressIndicatorDefaults.TrackHeight)
            .fillMaxWidth()
    ) {
        val strokeWidth = size.height
        drawLinearIndicatorTrack(trackColor, strokeWidth, strokeCap)
        if (firstLineHead.value - firstLineTail.value > 0) {
            drawLinearIndicator(
                firstLineHead.value,
                firstLineTail.value,
                color,
                strokeWidth,
                strokeCap,
            )
        }
        if (secondLineHead.value - secondLineTail.value > 0) {
            drawLinearIndicator(
                secondLineHead.value,
                secondLineTail.value,
                color,
                strokeWidth,
                strokeCap,
            )
        }
    }
}

private fun DrawScope.drawLinearIndicator(
    startFraction: Float,
    endFraction: Float,
    color: Color,
    strokeWidth: Float,
    strokeCap: StrokeCap,
) {
    val width = size.width
    val height = size.height
    val yOffset = height / 2

    val isLtr = layoutDirection == LayoutDirection.Ltr
    val barStart = (if (isLtr) startFraction else 1f - endFraction) * width
    val barEnd = (if (isLtr) endFraction else 1f - startFraction) * width

    if (strokeCap == StrokeCap.Butt || height > width) {
        drawLine(color, Offset(barStart, yOffset), Offset(barEnd, yOffset), strokeWidth)
    } else {
        val strokeCapOffset = strokeWidth / 2
        val coerceRange = strokeCapOffset..(width - strokeCapOffset)
        val adjustedBarStart = barStart.coerceIn(coerceRange)
        val adjustedBarEnd = barEnd.coerceIn(coerceRange)

        if (abs(endFraction - startFraction) > 0) {
            drawLine(
                color,
                Offset(adjustedBarStart, yOffset),
                Offset(adjustedBarEnd, yOffset),
                strokeWidth,
                strokeCap,
            )
        }
    }
}

private fun DrawScope.drawLinearIndicatorTrack(
    color: Color,
    strokeWidth: Float,
    strokeCap: StrokeCap,
) = drawLinearIndicator(0f, 1f, color, strokeWidth, strokeCap)

object LinearProgressIndicatorDefaults {
    val Color: Color
        @Composable get() = AppTheme.colors.primary

    val TrackColor: Color
        @Composable get() = AppTheme.colors.transparent

    val TrackHeight = 4.dp
    val StrokeStyle: StrokeCap = StrokeCap.Round
    const val AnimationDuration = 1800

    const val FirstLineHeadDuration = 750
    const val FirstLineTailDuration = 850
    const val SecondLineHeadDuration = 567
    const val SecondLineTailDuration = 533

    const val FirstLineHeadDelay = 0
    const val FirstLineTailDelay = 333
    const val SecondLineHeadDelay = 1000
    const val SecondLineTailDelay = 1267

    val FirstLineHeadEasing = CubicBezierEasing(0.2f, 0f, 0.8f, 1f)
    val FirstLineTailEasing = CubicBezierEasing(0.4f, 0f, 1f, 1f)
    val SecondLineHeadEasing = CubicBezierEasing(0f, 0f, 0.65f, 1f)
    val SecondLineTailEasing = CubicBezierEasing(0.1f, 0f, 0.45f, 1f)
}

@Composable
@Preview
fun LinearProgressIndicatorPreview() {
    AppTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Determinate Progress", style = AppTheme.typography.body1)
            LinearProgressIndicator(progress = 0.7f)

            Text(text = "Indeterminate Progress", style = AppTheme.typography.body1)
            LinearProgressIndicator()
        }
    }
}