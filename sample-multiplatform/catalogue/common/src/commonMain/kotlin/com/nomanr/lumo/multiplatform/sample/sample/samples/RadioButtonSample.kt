package com.nomanr.lumo.multiplatform.sample.sample.samples

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nomanr.lumo.multiplatform.ui.AppTheme
import com.nomanr.lumo.multiplatform.ui.components.HorizontalDivider
import com.nomanr.lumo.multiplatform.ui.components.RadioButton
import com.nomanr.lumo.multiplatform.ui.components.Text
import com.nomanr.lumo.multiplatform.ui.components.card.CardDefaults
import com.nomanr.lumo.multiplatform.ui.components.card.OutlinedCard

@Composable
fun RadioButtonSample() {
    Column(
        modifier =
            Modifier
                .background(AppTheme.colors.background)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        RadioButtonShowcase()

        Spacer(modifier = Modifier.height(24.dp))

        RadioButtonExamples()
    }
}

@Composable
private fun RadioButtonShowcase() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        var radioState1 by remember { mutableIntStateOf(2) }

        Text(text = "RadioButton", style = AppTheme.typography.h4)

        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            RadioButton(selected = radioState1 == 1, onClick = { radioState1 = 1 })
            RadioButton(selected = radioState1 == 2, onClick = { radioState1 = 2 })
        }
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            RadioButton(selected = radioState1 == 1, enabled = false, onClick = { radioState1 = 1 })
            RadioButton(selected = radioState1 == 2, enabled = false, onClick = { radioState1 = 2 })
        }

        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            RadioButton(selected = radioState1 == 1, onClick = { radioState1 = 1 }) {
                Text(text = "First", style = AppTheme.typography.label1)
            }
            RadioButton(selected = radioState1 == 2, onClick = { radioState1 = 2 }) {
                Text(text = "Second", style = AppTheme.typography.label1)
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            RadioButton(selected = radioState1 == 1, enabled = false, onClick = { radioState1 = 1 }) {
                Text(text = "First", style = AppTheme.typography.label1)
            }
            RadioButton(selected = radioState1 == 2, enabled = false, onClick = { radioState1 = 2 }) {
                Text(text = "Second", style = AppTheme.typography.label1)
            }
        }
    }
}

@Composable
private fun RadioButtonExamples() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        RadioButtonExampleWithOutline()

        RadioButtonDifficultyExample()

        RadioButtonGenderExample()
    }
}

@Composable
private fun RadioButtonExampleWithOutline() {
    var selectedState by remember { mutableIntStateOf(1) }

    val selectedBorder = BorderStroke(3.dp, AppTheme.colors.primary)

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedCard(
            border = if (selectedState == 1) selectedBorder else CardDefaults.outlinedCardBorder(),
            onClick = { selectedState = 1 },
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
                RadioButton(selected = selectedState == 1)
            }
        }

        OutlinedCard(
            border = if (selectedState == 2) selectedBorder else CardDefaults.outlinedCardBorder(),
            onClick = { selectedState = 2 },
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
                RadioButton(selected = selectedState == 2)
            }
        }
    }
}

@Composable
private fun RadioButtonDifficultyExample() {
    var difficulty by remember { mutableIntStateOf(1) }
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
            }

            HorizontalDivider()

            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = "Easy")
                    RadioButton(selected = difficulty == 1, onClick = { difficulty = 1 })
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = "Medium")
                    RadioButton(selected = difficulty == 2, onClick = { difficulty = 2 })
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = "Hard")
                    RadioButton(selected = difficulty == 3, onClick = { difficulty = 3 })
                }
            }
        }
    }
}

@Composable
private fun RadioButtonGenderExample() {
    var gender by remember { mutableIntStateOf(1) }

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
                Text(text = "Gender", style = AppTheme.typography.h4)
            }

            HorizontalDivider()

            Column(modifier = Modifier.padding(16.dp)) {
                RadioButton(selected = gender == 1, onClick = { gender = 1 }) {
                    Text(text = "Male", style = AppTheme.typography.label1)
                }

                RadioButton(selected = gender == 2, onClick = { gender = 2 }) {
                    Text(text = "Female", style = AppTheme.typography.label1)
                }

                RadioButton(selected = gender == 3, onClick = { gender = 3 }) {
                    Text(text = "Other", style = AppTheme.typography.label1)
                }
            }
        }
    }
}
