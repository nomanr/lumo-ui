package com.nomanr.sample.ui.samples

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.nomanr.sample.ui.data.Component

object Samples {
    val components: Map<Component, @Composable (padding: PaddingValues, triggerBackAction: Int, interceptNavigateUp: (intercept: Boolean) -> Unit) -> Unit>
        get() = mapOf<Component, @Composable (
            padding: PaddingValues,
            triggerBackAction: Int,
            interceptNavigateUp: (intercept: Boolean) -> Unit
        ) -> Unit>(
            Component.Text to { padding, _, _ -> TextSample(padding) },
            Component.Button to { padding, _, _ -> ButtonSample(padding) },
            Component.Icon to { padding, _, _ -> IconSample(padding) },
            Component.IconButton to { padding, _, _ -> IconButtonSample(padding) },
            Component.Card to { padding, _, _ -> CardSample(padding) },
            Component.TopBar to { padding, triggerBackAction, interceptNavigateUp -> TopBarSample(padding, triggerBackAction, interceptNavigateUp) },
            Component.Accordion to { padding, _, _ -> AccordionSample(padding) },

        )

    fun hasComponent(componentName: String): Boolean {
        return components.keys.any { it.label.equals(componentName, ignoreCase = true) }
    }
}