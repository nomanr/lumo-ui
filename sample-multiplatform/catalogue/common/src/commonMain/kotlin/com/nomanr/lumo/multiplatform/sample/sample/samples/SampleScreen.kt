package com.nomanr.lumo.multiplatform.sample.sample.samples

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nomanr.lumo.multiplatform.sample.sample.Component
import com.nomanr.lumo.multiplatform.sample.sample.ComponentId
import com.nomanr.lumo.multiplatform.sample.sample.Samples
import com.nomanr.lumo.multiplatform.sample.sample.components.SampleScreenTopBar
import com.nomanr.lumo.multiplatform.ui.components.Scaffold
import com.nomanr.lumo.multiplatform.ui.components.Text

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
        Column(
            modifier =
                Modifier
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
