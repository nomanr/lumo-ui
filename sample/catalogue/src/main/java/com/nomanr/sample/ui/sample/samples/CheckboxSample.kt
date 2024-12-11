package com.nomanr.sample.ui.sample.samples

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.components.*

@Composable
fun CheckboxSample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.Start
    ) {

        RegularCheckboxSection()

        Spacer(modifier = Modifier.height(16.dp))

        TriStateCheckboxSection()
    }
}

@Composable
fun RegularCheckboxSection() {
    Text(text = "Regular Checkboxes", style = AppTheme.typography.h4)

    var firstCheckboxState by remember { mutableStateOf(false) }
    var secondCheckboxState by remember { mutableStateOf(false) }
    var thirdCheckboxState by remember { mutableStateOf(true) }

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = firstCheckboxState,
                onCheckedChange = { firstCheckboxState = it }
            )
            Text(text = "First Checkbox (Default Unchecked)", style = AppTheme.typography.label1)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = secondCheckboxState,
                onCheckedChange = { secondCheckboxState = it }
            )
            Text(text = "Second Checkbox (Interactive)", style = AppTheme.typography.label1)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = thirdCheckboxState,
                onCheckedChange = { thirdCheckboxState = it }
            )
            Text(text = "Third Checkbox (Default Checked)", style = AppTheme.typography.label1)
        }
    }
}

@Composable
fun TriStateCheckboxSection() {
    Text(text = "Tri-State Checkboxes", style = AppTheme.typography.h4)

    var firstTriState by remember { mutableStateOf(ToggleableState.Off) }
    var secondTriState by remember { mutableStateOf(ToggleableState.Indeterminate) }
    var thirdTriState by remember { mutableStateOf(ToggleableState.On) }

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TriStateCheckbox(
                state = firstTriState,
                onClick = { firstTriState = toggleTriState(firstTriState) }
            )
            Text(text = "First Tri-State (Default Off)", style = AppTheme.typography.label1)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            TriStateCheckbox(
                state = secondTriState,
                onClick = { secondTriState = toggleTriState(secondTriState) }
            )
            Text(text = "Second Tri-State (Indeterminate)", style = AppTheme.typography.label1)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            TriStateCheckbox(
                state = thirdTriState,
                onClick = { thirdTriState = toggleTriState(thirdTriState) }
            )
            Text(text = "Third Tri-State (Default On)", style = AppTheme.typography.label1)
        }
    }
}

private fun toggleTriState(currentState: ToggleableState): ToggleableState {
    return when (currentState) {
        ToggleableState.Off -> ToggleableState.Indeterminate
        ToggleableState.Indeterminate -> ToggleableState.On
        ToggleableState.On -> ToggleableState.Off
    }
}