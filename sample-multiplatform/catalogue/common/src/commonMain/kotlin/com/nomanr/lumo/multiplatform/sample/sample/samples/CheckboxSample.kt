package com.nomanr.lumo.multiplatform.sample.sample.samples

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import com.nomanr.lumo.multiplatform.ui.AppTheme
import com.nomanr.lumo.multiplatform.ui.components.Checkbox
import com.nomanr.lumo.multiplatform.ui.components.HorizontalDivider
import com.nomanr.lumo.multiplatform.ui.components.Text
import com.nomanr.lumo.multiplatform.ui.components.TriStateCheckbox
import com.nomanr.lumo.multiplatform.ui.components.card.CardDefaults
import com.nomanr.lumo.multiplatform.ui.components.card.OutlinedCard

@Composable
fun CheckboxSample() {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        CheckboxeShowcase()

        Spacer(modifier = Modifier.height(24.dp))

        CheckboxExamples()
    }
}

@Composable
private fun CheckboxeShowcase() {
    Text(text = "Checkbox", style = AppTheme.typography.h4)

    var checkbox1A by remember { mutableStateOf(false) }
    var checkbox1B by remember { mutableStateOf(true) }

    var checkbox2A by remember { mutableStateOf(ToggleableState.Off) }
    var checkbox2B by remember { mutableStateOf(ToggleableState.Off) }
    var checkbox2C by remember { mutableStateOf(ToggleableState.Off) }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(checked = checkbox1A, onCheckedChange = { checkbox1A = it })

            Checkbox(checked = checkbox1B, onCheckedChange = { checkbox1B = it })
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(checked = checkbox1A, enabled = false, onCheckedChange = { checkbox1A = it })

            Checkbox(checked = checkbox1B, enabled = false, onCheckedChange = { checkbox1B = it })
        }

        Row {
            TriStateCheckbox(state = checkbox2A, onClick = { checkbox2A = toggleTriState(checkbox2A) })

            TriStateCheckbox(state = checkbox2B, onClick = { checkbox2B = toggleTriState(checkbox2B) })

            TriStateCheckbox(state = checkbox2C, onClick = { checkbox2C = toggleTriState(checkbox2C) })
        }

        Row {
            TriStateCheckbox(state = checkbox2A, enabled = false, onClick = { checkbox2A = toggleTriState(checkbox2A) })

            TriStateCheckbox(state = checkbox2B, enabled = false, onClick = { checkbox2B = toggleTriState(checkbox2B) })

            TriStateCheckbox(state = checkbox2C, enabled = false, onClick = { checkbox2C = toggleTriState(checkbox2C) })
        }
    }
}

@Composable
private fun CheckboxExamples() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        ExampleWithOutline()

        DifficultyExample()
    }
}

@Composable
private fun ExampleWithOutline() {
    val checkedState1 = remember { mutableStateOf(false) }
    val checkedState2 = remember { mutableStateOf(false) }

    val border1 = if (checkedState1.value) BorderStroke(3.dp, AppTheme.colors.primary) else CardDefaults.outlinedCardBorder()
    val border2 = if (checkedState2.value) BorderStroke(3.dp, AppTheme.colors.primary) else CardDefaults.outlinedCardBorder()

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedCard(
            border = border1,
        ) {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(modifier = Modifier.padding(end = 12.dp), text = "Weekly Discount", style = AppTheme.typography.h4)
                Checkbox(checked = checkedState1.value, onCheckedChange = { checkedState1.value = it })
            }
        }

        OutlinedCard(
            border = border2,
        ) {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(modifier = Modifier.padding(end = 12.dp), text = "Monthly Discount", style = AppTheme.typography.h4)
                Checkbox(checked = checkedState2.value, onCheckedChange = { checkedState2.value = it })
            }
        }
    }
}

@Composable
private fun DifficultyExample() {
    var all by remember { mutableStateOf(ToggleableState.Indeterminate) }
    var easy by remember { mutableStateOf(true) }
    var medium by remember { mutableStateOf(false) }
    var hard by remember { mutableStateOf(false) }

    LaunchedEffect(easy, medium, hard) {
        all =
            when {
                easy && medium && hard -> ToggleableState.On
                !easy && !medium && !hard -> ToggleableState.Off
                else -> ToggleableState.Indeterminate
            }
    }

    OutlinedCard {
        Column {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = "Difficulty", style = AppTheme.typography.h4)
                TriStateCheckbox(state = all, onClick = {
                    if (all == ToggleableState.On) {
                        easy = false
                        medium = false
                        hard = false
                    } else {
                        easy = true
                        medium = true
                        hard = true
                    }
                })
            }

            HorizontalDivider()

            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = "Easy")
                    Checkbox(checked = easy, onCheckedChange = { easy = it })
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = "Medium")
                    Checkbox(checked = medium, onCheckedChange = { medium = it })
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = "Hard")
                    Checkbox(checked = hard, onCheckedChange = { hard = it })
                }
            }
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
