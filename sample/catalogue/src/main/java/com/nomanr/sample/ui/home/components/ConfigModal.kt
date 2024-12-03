package com.nomanr.sample.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.LocalTypography
import com.nomanr.sample.ui.components.ModalBottomSheet
import com.nomanr.sample.ui.components.RadioButton
import com.nomanr.sample.ui.components.Slider
import com.nomanr.sample.ui.components.Text
import com.nomanr.sample.ui.configs.LocalFontScaleState

@Composable
fun ConfigModal(isVisible: Boolean, onDismiss: () -> Unit) {
    CompositionLocalProvider(LocalTypography provides AppTheme.originalScaleTypography) {
        ModalBottomSheet(isVisible = isVisible, onDismissRequest = onDismiss) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {

                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text("Configurations", style = AppTheme.typography.h2)
                }

                Spacer(modifier = Modifier.height(24.dp))

                ConfigSection("Font Scale") {
                    FontScale()
                }
            }
        }
    }
}

@Composable
fun FontScale() {
    val fontScaleState = LocalFontScaleState.current

    var isSystemFontScale: Boolean by remember {
        mutableStateOf(fontScaleState.systemFontScale == fontScaleState.fontScale)
    }

    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = isSystemFontScale, onClick = {
                isSystemFontScale = true
                fontScaleState.resetFontScale()
            })
            Text(text = "System", style = AppTheme.typography.body3)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = !isSystemFontScale, onClick = { isSystemFontScale = false })
            Text(text = "Custom", style = AppTheme.typography.body3)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {


            Slider(
                modifier = Modifier.weight(0.8f),
                value = fontScaleState.fontScale,
                valueRange = (fontScaleState.systemFontScale)..2.5f,
                onValueChange = { fontScaleState.updateFontScale(it) },
                enabled = !isSystemFontScale
            )

            Box(modifier = Modifier.weight(0.2f), contentAlignment = Alignment.Center) {
                Text("%.2f".format(fontScaleState.fontScale), style = AppTheme.typography.label2)
            }

        }
    }
}


@Composable
fun ConfigSection(title: String, content: @Composable () -> Unit) {


    Text("Font Scale", style = AppTheme.typography.label1)

    Spacer(modifier = Modifier.height(8.dp))

    content()
}