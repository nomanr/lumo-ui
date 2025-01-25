package com.nomanr.sample.ui.sample.samples

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.LocalContentColor
import com.nomanr.sample.ui.components.HorizontalDivider
import com.nomanr.sample.ui.components.Icon
import com.nomanr.sample.ui.components.Switch
import com.nomanr.sample.ui.components.Text
import com.nomanr.sample.ui.components.card.CardDefaults
import com.nomanr.sample.ui.components.card.OutlinedCard

@Composable
fun SwitchSample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {

        SwitchShowcase()

        Spacer(modifier = Modifier.height(24.dp))

        SwitchExamples()

    }

}

@Composable
private fun SwitchShowcase() {
    val checked = remember {
        mutableStateOf(false)
    }

    Text(text = "Switch", style = AppTheme.typography.h4)

    Column(
        modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Switch(checked = checked.value, onCheckedChange = {
                checked.value = it
            })
            Switch(checked = checked.value, enabled = false, onCheckedChange = {
                checked.value = it
            })
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Switch(checked = checked.value, thumbContent = {
                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .clip(CircleShape)
                        .background(LocalContentColor.current)
                )
            }, onCheckedChange = {
                checked.value = it
            })
            Switch(checked = checked.value, enabled = false, thumbContent = {
                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .clip(CircleShape)
                        .background(LocalContentColor.current)
                )
            }, onCheckedChange = {
                checked.value = it
            })
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Switch(checked = checked.value, thumbContent = {
                Icon(
                    modifier = Modifier.size(12.dp), imageVector = Icons.Default.Check, contentDescription = "Checked"
                )
            }, onCheckedChange = {
                checked.value = it
            })
            Switch(checked = checked.value, enabled = false, thumbContent = {
                Icon(
                    modifier = Modifier.size(12.dp), imageVector = Icons.Default.Check, contentDescription = "Checked"
                )
            }, onCheckedChange = {
                checked.value = it
            })
        }

    }
}

@Composable
private fun SwitchExamples() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

        SwitchExampleWithOutline()

        SwitchDifficultyExample()

    }
}


@Composable
private fun SwitchExampleWithOutline() {
    var checkedState1 by remember { mutableStateOf(false) }
    var checkedState2 by remember { mutableStateOf(false) }

    val selectedBorder = BorderStroke(3.dp, AppTheme.colors.primary)

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedCard(border = if (checkedState1) selectedBorder else CardDefaults.outlinedCardBorder(),
            onClick = { checkedState1 = !checkedState1 }

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(modifier = Modifier.padding(end = 12.dp), text = "Weekly Discount", style = AppTheme.typography.h4)
                Switch(checked = checkedState1, onCheckedChange = { checkedState1 = it })
            }
        }


        OutlinedCard(border = if (checkedState2) selectedBorder else CardDefaults.outlinedCardBorder(),
            onClick = { checkedState2 = !checkedState2 }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(modifier = Modifier.padding(end = 12.dp), text = "Monthly Discount", style = AppTheme.typography.h4)
                Switch(checked = checkedState2, onCheckedChange = { checkedState2 = it })
            }
        }
    }
}


@Composable
private fun SwitchDifficultyExample() {
    var easy by remember { mutableStateOf(true) }
    var medium by remember { mutableStateOf(false) }
    var hard by remember { mutableStateOf(false) }

    OutlinedCard {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Difficulty", style = AppTheme.typography.h4)
            }

            HorizontalDivider()

            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Easy")
                    Switch(checked = easy, onCheckedChange = { easy = it })
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Medium")
                    Switch(checked = medium, onCheckedChange = { medium = it })
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Hard")
                    Switch(checked = hard, onCheckedChange = { hard = it })
                }
            }
        }
    }
}