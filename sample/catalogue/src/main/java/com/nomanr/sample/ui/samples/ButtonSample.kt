package com.nomanr.sample.ui.samples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.components.Button
import com.nomanr.sample.ui.components.ButtonVariant
import com.nomanr.sample.ui.components.Text

@Composable
fun ButtonSample(padding: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues = padding)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .background(AppTheme.colors.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Primary Buttons", style = AppTheme.typography.h2)

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(text = "PrimaryFilled", variant = ButtonVariant.Primary, onClick = {})

                Button(
                    text = "Disabled", variant = ButtonVariant.Primary, enabled = false
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(text = "PrimaryOutlined", variant = ButtonVariant.PrimaryOutlined, onClick = {})

                Button(
                    text = "Disabled", variant = ButtonVariant.PrimaryOutlined, enabled = false
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(text = "PrimaryElevated", variant = ButtonVariant.PrimaryElevated, onClick = {})

                Button(
                    text = "Disabled", variant = ButtonVariant.PrimaryElevated, enabled = false
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(text = "PrimaryGhost", variant = ButtonVariant.PrimaryGhost, onClick = {})

                Button(
                    text = "Disabled", variant = ButtonVariant.PrimaryGhost, enabled = false
                )
            }
        }
    }
}