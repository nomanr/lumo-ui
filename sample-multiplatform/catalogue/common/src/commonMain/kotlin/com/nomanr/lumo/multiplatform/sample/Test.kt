package com.nomanr.lumo.multiplatform.sample

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nomanr.lumo.multiplatform.ui.components.Text

@Composable
fun Test() {
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("A", "B", "C")
    var selectedOption by remember { mutableStateOf(options[0]) }


    Box(
        modifier = Modifier.clickable { expanded = true }.padding(16.dp).border(1.dp, Color.Gray, RoundedCornerShape(8.dp)).padding(8.dp),
    ) {
        Text(text = selectedOption)

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEach { option ->
                Text(
                    text = option,
                    modifier = Modifier.clickable {
                        selectedOption = option
                        expanded = false
                    }.padding(16.dp),
                )
            }
        }
    }
}
