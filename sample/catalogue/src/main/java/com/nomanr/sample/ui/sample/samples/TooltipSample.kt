package com.nomanr.sample.ui.sample.samples

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.components.Button
import com.nomanr.sample.ui.components.Icon
import com.nomanr.sample.ui.components.IconButton
import com.nomanr.sample.ui.components.Text
import com.nomanr.sample.ui.components.Tooltip
import com.nomanr.sample.ui.components.TooltipBox
import com.nomanr.sample.ui.components.rememberTooltipState

@Composable
fun TooltipSample(

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text("Long tap to show.", style = AppTheme.typography.h4)

        Spacer(modifier = Modifier.size(36.dp))


        Box(
            modifier = Modifier.padding(top = 16.dp), contentAlignment = Alignment.Center
        ) {
            TooltipBox(state = rememberTooltipState(initialIsVisible = true), tooltip = {
                Tooltip { Text("Auto-dismisses", style = AppTheme.typography.label1) }
            }) {
                Button {
                    Text("Basic tooltip", style = AppTheme.typography.button)
                }
            }
        }

        Box(
            modifier = Modifier.padding(top = 42.dp), contentAlignment = Alignment.Center
        ) {
            TooltipBox(state = rememberTooltipState(initialIsVisible = true), tooltip = {
                Tooltip(
                    caretSize = DpSize.Unspecified
                ) { Text("Auto-dismisses", style = AppTheme.typography.label1) }
            }) {
                Button {
                    Text("Without Caret", style = AppTheme.typography.button)
                }
            }
        }



        Box(
            modifier = Modifier.padding(top = 42.dp), contentAlignment = Alignment.Center
        ) {
            TooltipBox(state = rememberTooltipState(
                initialIsVisible = true, isPersistent = true
            ), tooltip = {
                Tooltip { Text("Dismiss on tap outside", style = AppTheme.typography.label1) }
            }) {
                IconButton {
                    Icon(Icons.Default.Favorite, contentDescription = "Favorite")
                }
            }
        }


        val tooltipState = rememberTooltipState(true)
        Box(
            modifier = Modifier.padding(top = 150.dp), contentAlignment = Alignment.Center
        ) {
            TooltipBox(state = tooltipState, tooltip = {
                Tooltip {
                    Box(modifier = Modifier.padding(16.dp)) {
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Icon(Icons.Default.Warning, contentDescription = "Warn")
                            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text("Place order", style = AppTheme.typography.h4)
                                Text("By clicking this button, you'll be placing your order!", style = AppTheme.typography.body2)
                            }
                            Icon(
                                modifier = Modifier.clickable { tooltipState.dismiss() },
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close"
                            )
                        }
                    }
                }
            }) {
                Button(modifier = Modifier.fillMaxWidth()) {
                    Text("Place Order", style = AppTheme.typography.button)
                }
            }
        }

    }
}