package com.nomanr.sample.ui.sample

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nomanr.sample.ui.components.Scaffold
import com.nomanr.sample.ui.components.Text
import com.nomanr.sample.ui.sample.components.SampleScreenTopBar

@Composable
fun SampleScreen(componentId: ComponentId, navigateUp: () -> Unit = {}) {
    val component = Component.getById(componentId)
    val sample = Samples.components[component.id]

    if (!component.showTopBar && sample != null) {
        sample(navigateUp)
        return
    }


    Scaffold(topBar = {
        SampleScreenTopBar(title = component.label, onBack = {
            navigateUp()
        })
    }) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            if (sample != null) {
                sample(navigateUp)
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Sample not found for ${component.id.label}")
                }
            }
        }
    }
}

