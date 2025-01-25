package com.nomanr.sample.ui.sample.samples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.components.Chip
import com.nomanr.sample.ui.components.ElevatedChip
import com.nomanr.sample.ui.components.Icon
import com.nomanr.sample.ui.components.OutlinedChip
import com.nomanr.sample.ui.components.Text
import com.nomanr.sample.ui.utils.composableOrNull

@Composable
fun ChipSample() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        ChipShowcase()
        ChipExamples()
    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ChipShowcase() {

    Column(modifier = Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(text = "Chip", style = AppTheme.typography.h4)

        Spacer(modifier = Modifier)


        val shapes = listOf(RoundedCornerShape(4.dp), CircleShape)

        shapes.forEach { shape ->
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Chip(shape = shape) {
                    Text(text = "Normal", style = AppTheme.typography.label3)
                }
                Chip(selected = true, shape = shape) {
                    Text(text = "Selected", style = AppTheme.typography.label3)
                }
                Chip(enabled = false, shape = shape) {
                    Text(text = "Disabled", style = AppTheme.typography.label3)
                }
            }

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ElevatedChip(shape = shape) {
                    Text(text = "Normal", style = AppTheme.typography.label3)
                }
                ElevatedChip(selected = true, shape = shape) {
                    Text(text = "Selected", style = AppTheme.typography.label3)
                }
                ElevatedChip(enabled = false, shape = shape) {
                    Text(text = "Disabled", style = AppTheme.typography.label3)
                }
            }

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedChip(shape = shape) {
                    Text(text = "Normal", style = AppTheme.typography.label3)
                }
                OutlinedChip(selected = true, shape = shape) {
                    Text(text = "Selected", style = AppTheme.typography.label3)
                }
                OutlinedChip(enabled = false, shape = shape) {
                    Text(text = "Disabled", style = AppTheme.typography.label3)
                }
            }
        }

        val pairs = listOf(
            Pair(Icons.Default.Favorite, null),
            Pair(null, Icons.Default.Close),
            Pair(Icons.Default.Favorite, Icons.Default.Close),
        )

        pairs.forEach { pair ->
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Chip(leadingIcon = composableOrNull(pair.first != null) {
                    Icon(imageVector = pair.first!!, contentDescription = "Leading Icon")
                }, trailingIcon = composableOrNull(pair.second != null) {
                    Icon(imageVector = pair.second!!, contentDescription = "Trailing Icon")
                }) {
                    Text(text = "Chip", style = AppTheme.typography.label3)
                }
                Chip(selected = true, leadingIcon = composableOrNull(pair.first != null) {
                    Icon(imageVector = pair.first!!, contentDescription = "Leading Icon")
                }, trailingIcon = composableOrNull(pair.second != null) {
                    Icon(imageVector = pair.second!!, contentDescription = "Trailing Icon")
                }) {
                    Text(text = "Chip", style = AppTheme.typography.label3)
                }
                Chip(enabled = false, leadingIcon = composableOrNull(pair.first != null) {
                    Icon(imageVector = pair.first!!, contentDescription = "Leading Icon")
                }, trailingIcon = composableOrNull(pair.second != null) {
                    Icon(imageVector = pair.second!!, contentDescription = "Trailing Icon")
                }) {
                    Text(text = "Chip", style = AppTheme.typography.label3)
                }
            }

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ElevatedChip(leadingIcon = composableOrNull(pair.first != null) {
                    Icon(imageVector = pair.first!!, contentDescription = "Leading Icon")
                }, trailingIcon = composableOrNull(pair.second != null) {
                    Icon(imageVector = pair.second!!, contentDescription = "Trailing Icon")
                }) {
                    Text(text = "Chip", style = AppTheme.typography.label3)
                }
                ElevatedChip(selected = true, leadingIcon = composableOrNull(pair.first != null) {
                    Icon(imageVector = pair.first!!, contentDescription = "Leading Icon")
                }, trailingIcon = composableOrNull(pair.second != null) {
                    Icon(imageVector = pair.second!!, contentDescription = "Trailing Icon")
                }) {
                    Text(text = "Chip", style = AppTheme.typography.label3)
                }
                ElevatedChip(enabled = false, leadingIcon = composableOrNull(pair.first != null) {
                    Icon(imageVector = pair.first!!, contentDescription = "Leading Icon")
                }, trailingIcon = composableOrNull(pair.second != null) {
                    Icon(imageVector = pair.second!!, contentDescription = "Trailing Icon")
                }) {
                    Text(text = "Chip", style = AppTheme.typography.label3)
                }
            }

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedChip(leadingIcon = composableOrNull(pair.first != null) {
                    Icon(imageVector = pair.first!!, contentDescription = "Leading Icon")
                }, trailingIcon = composableOrNull(pair.second != null) {
                    Icon(imageVector = pair.second!!, contentDescription = "Trailing Icon")
                }) {
                    Text(text = "Chip", style = AppTheme.typography.label3)
                }
                OutlinedChip(selected = true, leadingIcon = composableOrNull(pair.first != null) {
                    Icon(imageVector = pair.first!!, contentDescription = "Leading Icon")
                }, trailingIcon = composableOrNull(pair.second != null) {
                    Icon(imageVector = pair.second!!, contentDescription = "Trailing Icon")
                }) {
                    Text(text = "Chip", style = AppTheme.typography.label3)
                }
                OutlinedChip(enabled = false, leadingIcon = composableOrNull(pair.first != null) {
                    Icon(imageVector = pair.first!!, contentDescription = "Leading Icon")
                }, trailingIcon = composableOrNull(pair.second != null) {
                    Icon(imageVector = pair.second!!, contentDescription = "Trailing Icon")
                }) {
                    Text(text = "Chip", style = AppTheme.typography.label3)
                }
            }
        }

    }

}

@Composable
private fun ChipExamples() {
    Spacer(modifier = Modifier.size(32.dp))

    Text(text = "Examples", style = AppTheme.typography.h4)

    FavoriteActivityExample()

    SnacksExample()
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FavoriteActivityExample() {

    var selectedActivities by remember { mutableStateOf<Set<String>>(emptySet()) }

    Text(
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
        text = "Favorite activity type ",
        style = AppTheme.typography.label1
    )

    val activities = listOf(
        "Hiking", "Cycling", "Swimming", "Running", "Gym", "Backpacking", "Walking", "Road biking", "Off-road driving"
    )

    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        activities.forEach { activity ->

            val isSelected = selectedActivities.contains(activity)
            Chip(selected = isSelected, onClick = {
                    if (selectedActivities.contains(activity)) {
                        selectedActivities -= activity
                    } else {
                        selectedActivities += activity
                    }
            }, leadingIcon = composableOrNull(isSelected){
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = Icons.Default.Check, contentDescription = "Selected")
            }) {
                Text(text = activity, style = AppTheme.typography.label2)
            }
        }
    }


}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SnacksExample() {

    var selectedSnacks by remember { mutableStateOf<Set<String>>(emptySet()) }

    Text(
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
        text = "Select snacks",
        style = AppTheme.typography.label1
    )

    val snacks = listOf(
        "Chips", "Cookies", "Candy", "Chocolate", "Popcorn", "Pretzels", "Nuts", "Fruit", "Vegetables"
    )


    if(selectedSnacks.isNotEmpty()){
        Text(
            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
            text = "Selected",
            style = AppTheme.typography.label1
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            selectedSnacks.forEach { activity ->

                val isSelected = selectedSnacks.contains(activity)
                OutlinedChip (selected = isSelected, onClick = {
                    selectedSnacks -= activity
                }, trailingIcon = composableOrNull(isSelected){
                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.Default.Close, contentDescription = "Close")
                }, shape = CircleShape) {
                    Text(text = activity, style = AppTheme.typography.label2)
                }
            }
        }

    }

    Text(
        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
        text = "Available Snacks",
        style = AppTheme.typography.label1
    )


    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        snacks.filter { !selectedSnacks.contains(it) }.forEach { activity ->

            OutlinedChip (onClick = {
                if (selectedSnacks.contains(activity)) {
                    selectedSnacks -= activity
                } else {
                    selectedSnacks += activity
                }
            }, shape = CircleShape) {
                Text(text = activity, style = AppTheme.typography.label2)
            }
        }
    }


}

