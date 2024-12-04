package com.nomanr.sample.ui.samples

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.nomanr.sample.ui.data.Component

object Samples {
    val components =
        mapOf<Component, @Composable (padding: PaddingValues, interceptNavigateUp: (intercept: Boolean) -> Unit) -> Unit>(
            Component.Text to { padding, _ -> TextSample(padding) },
            Component.Button to { padding, _ -> ButtonSample(padding) },
            Component.Icon to { padding, _ -> IconSample(padding) },
        )
}