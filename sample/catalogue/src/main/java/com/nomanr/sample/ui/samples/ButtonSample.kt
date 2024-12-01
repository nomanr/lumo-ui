package com.nomanr.sample.ui.samples

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.components.DestructiveButtonPreview
import com.nomanr.sample.ui.components.PrimaryButtonPreview
import com.nomanr.sample.ui.components.SecondaryButtonPreview

@Composable
fun ButtonSample(padding: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues = padding)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        PrimaryButtonPreview()
        SecondaryButtonPreview()
        DestructiveButtonPreview()
    }
}