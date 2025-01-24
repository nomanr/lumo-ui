
package com.nomanr.sample.ui.sample.samples

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.nomanr.composables.bottomsheet.rememberModalBottomSheetState
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.components.Button
import com.nomanr.sample.ui.components.ButtonVariant
import com.nomanr.sample.ui.components.ModalBottomSheet
import com.nomanr.sample.ui.components.Text
import com.nomanr.sample.ui.sample.components.Skeleton

@Composable
private fun rememberModalSheetState() = remember { ModalSheetState() }

@Composable
fun ModalBottomSheetSample() {
    val state = rememberModalSheetState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Spacer(modifier = Modifier)

        Button(modifier = Modifier.fillMaxWidth(),
            text = "Simple Bottom Sheet",
            variant = ButtonVariant.PrimaryOutlined,
            onClick = { state.showSimpleSheet = true })

        Button(modifier = Modifier.fillMaxWidth(),
            text = "Scrollable Content Bottom Sheet",
            variant = ButtonVariant.PrimaryOutlined,
            onClick = { state.showScrollableSheet = true })

        Button(modifier = Modifier.fillMaxWidth(),
            text = "Custom Handle Bottom Sheet",
            variant = ButtonVariant.PrimaryOutlined,
            onClick = { state.showCustomDragHandleSheet = true })

        Button(modifier = Modifier.fillMaxWidth(),
            text = "Sheet Progress",
            variant = ButtonVariant.PrimaryOutlined,
            onClick = { state.showProgressSheet = true })
    }

    ModalBottomSheetSamples(state)
}

@Composable
private fun ModalBottomSheetSamples(state: ModalSheetState) {
    ModalBottomSheet(isVisible = state.showSimpleSheet, onDismissRequest = { state.showSimpleSheet = false }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("This is a simple bottom sheet.", style = AppTheme.typography.h4)
            Button(
                text = "Close",
                variant = ButtonVariant.Primary,
                onClick = { state.showSimpleSheet = false },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    ModalBottomSheet(modifier = Modifier.padding(top = 100.dp),
        isVisible = state.showScrollableSheet,
        onDismissRequest = { state.showScrollableSheet = false }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(30) {
                Skeleton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                )
            }
            Button(
                text = "Close",
                variant = ButtonVariant.Primary,
                onClick = { state.showScrollableSheet = false },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    ModalBottomSheet(isVisible = state.showCustomDragHandleSheet,
        onDismissRequest = { state.showCustomDragHandleSheet = false },
        dragHandle = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                ProgressBarDragHandle()
            }
        }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            repeat(5) { index ->
                Skeleton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )
            }
            Button(
                text = "Close",
                variant = ButtonVariant.Primary,
                onClick = { state.showCustomDragHandleSheet = false },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    val sheetState = rememberModalBottomSheetState()

    val sheetProgress = sheetState.calculateSheetProgress()

    ModalBottomSheet(sheetState = sheetState,
        isVisible = state.showProgressSheet,
        onDismissRequest = { state.showProgressSheet = false },
        dragHandle = {
            AnimateDragHandle(progress = sheetProgress)
        }) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))



            Box(contentAlignment = Alignment.Center) {
                Skeleton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                )

                Text("Progress $sheetProgress", style = AppTheme.typography.body2)
            }

            Spacer(modifier = Modifier.height(8.dp))

            repeat(50) {
                Skeleton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun ProgressBarDragHandle() {
    var progress by remember { androidx.compose.runtime.mutableFloatStateOf(0f) }

    val animatedProgress = animateFloatAsState(
        targetValue = progress, animationSpec = tween(durationMillis = 2000), label = "progressBarAnimation"
    )

    LaunchedEffect(Unit) {
        progress = 1f
    }

    Box(
        modifier = Modifier
            .width(56.dp)
            .fillMaxWidth()
            .padding(12.dp)
            .height(6.dp)
            .clip(RoundedCornerShape(50))
            .background(AppTheme.colors.secondary)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(animatedProgress.value)
                .height(6.dp)
                .clip(RoundedCornerShape(50))
                .background(AppTheme.colors.primary)
        )
    }
}

@Composable
private fun AnimateDragHandle(progress: Float) {
    Box(
        modifier = Modifier
            .width(100.dp)
            .fillMaxWidth()
            .padding(12.dp)
            .height(6.dp)
            .clip(RoundedCornerShape(50))
            .background(AppTheme.colors.secondary)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress)
                .height(6.dp)
                .clip(RoundedCornerShape(50))
                .background(AppTheme.colors.primary)
        )
    }
}

private class ModalSheetState {
    var showSimpleSheet by mutableStateOf(false)
    var showScrollableSheet by mutableStateOf(false)
    var showCustomDragHandleSheet by mutableStateOf(false)
    var showProgressSheet by mutableStateOf(false)
}
