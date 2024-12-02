package com.nomanr.sample.ui.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nomanr.sample.ui.components.ModalBottomSheet
import com.nomanr.sample.ui.components.Text

@Composable
fun ConfigModal(isVisible: Boolean, onDismiss: () -> Unit) {

    ModalBottomSheet(isVisible = isVisible, onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            repeat(100) { it ->
                Text(text = "Item $it")
            }
        }
    }

}