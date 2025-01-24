package com.nomanr.sample.ui.sample.samples

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VideoFile
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.components.Button
import com.nomanr.sample.ui.components.Icon
import com.nomanr.sample.ui.components.Text
import com.nomanr.sample.ui.components.card.OutlinedCard
import com.nomanr.sample.ui.components.progress_indicators.CircularProgressIndicator
import com.nomanr.sample.ui.components.progress_indicators.LinearProgressIndicator

@Composable
fun ProgressIndicatorSample() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        LinearProgressIndicatorDemo()

        Spacer(modifier = Modifier.padding(24.dp))

        CircularProgressIndicatorDemo()

        Spacer(modifier = Modifier.padding(24.dp))

        IndicatorExamples()
    }
}

@Composable
private fun LinearProgressIndicatorDemo() {
    var progress by remember { mutableFloatStateOf(0f) }
    val animatedProgress = animateFloatAsState(
        targetValue = progress, animationSpec = tween(durationMillis = 5000), label = "progress"
    )

    LaunchedEffect(Unit) {
        progress = 1f
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Text(text = "Linear progress indicator", style = AppTheme.typography.h4)

        LinearProgressIndicator()

        LinearProgressIndicator(
            trackColor = AppTheme.colors.secondary,
        )


        Row(verticalAlignment = Alignment.CenterVertically) {
            LinearProgressIndicator(
                modifier = Modifier.weight(1f), progress = animatedProgress.value
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "${(animatedProgress.value * 100).toInt()}%", style = AppTheme.typography.label3)
        }
    }

}

@Composable
private fun CircularProgressIndicatorDemo() {
    var progress by remember { mutableFloatStateOf(0f) }
    val animatedProgress = animateFloatAsState(
        targetValue = progress, animationSpec = tween(durationMillis = 5000), label = "progress"
    )

    LaunchedEffect(Unit) {
        progress = 1f
    }

    Text(text = "Circular progress indicator", style = AppTheme.typography.h4)
    Spacer(modifier = Modifier.height(24.dp))

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(24.dp),
    ) {

        CircularProgressIndicator()

        CircularProgressIndicator(
            trackColor = AppTheme.colors.secondary,
        )

        Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                modifier = Modifier.size(92.dp),
                progress = animatedProgress.value
            )
            Text(text = "${(animatedProgress.value * 100).toInt()}%", style = AppTheme.typography.label3)
        }
    }

}

@Composable
private fun IndicatorExamples() {
    var progress by remember { mutableFloatStateOf(0f) }
    val animatedProgress = animateFloatAsState(
        targetValue = progress, animationSpec = tween(durationMillis = 5000), label = "progress"
    )

    LaunchedEffect(Unit) {
        progress = 1f
    }

    Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(24.dp)) {
        Text(text = "Examples", style = AppTheme.typography.h4)

        Button(onClick = { /*TODO*/ }) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Loading", style = AppTheme.typography.button)
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                )
            }
        }

        OutlinedCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.VideoFile,
                    contentDescription = "Video",
                )


                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Intro.mp4", style = AppTheme.typography.label1)
                        Text(text = "${(animatedProgress.value * 100).toInt()}%", style = AppTheme.typography.label3)
                    }
                    LinearProgressIndicator(
                        progress = animatedProgress.value,
                        trackColor = AppTheme.colors.secondary,
                    )
                }

            }
        }
    }
}