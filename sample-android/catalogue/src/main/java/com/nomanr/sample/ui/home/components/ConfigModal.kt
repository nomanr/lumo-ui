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
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.LocalTypography
import com.nomanr.sample.ui.components.ModalBottomSheet
import com.nomanr.sample.ui.components.RadioButton
import com.nomanr.sample.ui.components.Slider
import com.nomanr.sample.ui.components.Text
import com.nomanr.sample.ui.configs.LocalAppConfigState

@Composable
fun ConfigModal(isVisible: Boolean, onDismiss: () -> Unit) {
    val appConfigState = LocalAppConfigState.current

    CompositionLocalProvider(
        LocalTypography provides AppTheme.originalScaleTypography,
        LocalLayoutDirection provides appConfigState.systemLayoutDirection
    ) {
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

                ConfigSection("Layout Direction") {
                    LayoutDirectionSettings()
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
fun LayoutDirectionSettings() {
    val appConfigState = LocalAppConfigState.current

    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(24.dp)) {
        RadioButton(selected = appConfigState.layoutDirection == LayoutDirection.Ltr, onClick = {
            appConfigState.updateLayoutDirection(LayoutDirection.Ltr)
        }) {
            Text(text = "LTR", style = AppTheme.typography.body3)
        }

        RadioButton(selected = appConfigState.layoutDirection == LayoutDirection.Rtl, onClick = {
            appConfigState.updateLayoutDirection(LayoutDirection.Rtl)
        }) {
            Text(text = "RTL", style = AppTheme.typography.body3)
        }
    }
}

@Composable
fun FontScale() {
    val appConfigState = LocalAppConfigState.current

    var isSystemFontScale: Boolean by remember {
        mutableStateOf(appConfigState.systemFontScale == appConfigState.fontScale)
    }

    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = isSystemFontScale, onClick = {
                isSystemFontScale = true
                appConfigState.resetFontScale()
            }) {
                Text(text = "System", style = AppTheme.typography.body3)
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = !isSystemFontScale, onClick = { isSystemFontScale = false }) {
                Text(text = "Custom", style = AppTheme.typography.body3)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {


            Slider(
                modifier = Modifier.weight(0.8f),
                value = appConfigState.fontScale,
                valueRange = (appConfigState.systemFontScale)..2.5f,
                onValueChange = { appConfigState.updateFontScale(it) },
                enabled = !isSystemFontScale
            )

            Box(modifier = Modifier.weight(0.2f), contentAlignment = Alignment.Center) {
                Text("%.2f".format(appConfigState.fontScale), style = AppTheme.typography.label2)
            }

        }
    }
}


@Composable
fun ConfigSection(title: String, content: @Composable () -> Unit) {

    Text(title, style = AppTheme.typography.label1)

    Spacer(modifier = Modifier.height(8.dp))

    content()
}

