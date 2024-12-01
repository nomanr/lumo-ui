package com.nomanr.sample.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nomanr.composables.bottomsheet.BasicModalBottomSheet
import com.nomanr.composables.bottomsheet.SheetState
import com.nomanr.composables.bottomsheet.rememberModalBottomSheetState
import com.nomanr.sample.ui.AppTheme

@Composable
fun ModalBottomSheet(
    sheetState: SheetState = rememberModalBottomSheetState(),
    isVisible: Boolean,
    onDismissRequest: () -> Unit,
    sheetGesturesEnabled: Boolean = true,
    dragHandle: @Composable (() -> Unit)? = { BottomSheetDefaults.DragHandle() },
    content: @Composable ColumnScope.() -> Unit
) {
    if (isVisible) {
        BasicModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = onDismissRequest,
            sheetGesturesEnabled = sheetGesturesEnabled,
            containerColor = AppTheme.colors.background,
            scrimColor = AppTheme.colors.scrim,
            shape = BottomSheetDefaults.ModalBottomSheetShape,
            dragHandle = dragHandle,
            content = content
        )
    }
}

internal object BottomSheetDefaults {

    private val DragHandleHeight = 6.dp
    private val DragHandleWidth = 36.dp
    private val DragHandleShape = RoundedCornerShape(50)
    private val DragHandlePadding = 12.dp
    val ModalBottomSheetShape = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 16.dp,
    )


    @Composable
    fun DragHandle() {
        Box(
            modifier = Modifier
                .padding(DragHandlePadding)
                .background(color = Color.Companion.Unspecified, shape = RoundedCornerShape(12.dp))
        ) {
            Spacer(
                Modifier
                    .size(width = DragHandleWidth, height = DragHandleHeight)
                    .background(color = AppTheme.colors.secondary, shape = DragHandleShape)
            )
        }
    }
}


@Preview
@Composable
fun ModalBottomSheetPreview() {
    ModalBottomSheet(isVisible = true, onDismissRequest = { }) {
        Column {
            for (i in 0..10) {
                Box(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                        .background(color = if (i % 2 == 0) Color.Red else Color.Blue)
                )
            }
        }
    }
}
