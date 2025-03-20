package com.nomanr.lumo.multiplatform.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nomanr.lumo.multiplatform.sample.sample.components.SampleScreenTopBar
import com.nomanr.lumo.multiplatform.ui.AppTheme
import com.nomanr.lumo.multiplatform.ui.components.DropdownMenu
import com.nomanr.lumo.multiplatform.ui.components.Scaffold
import com.nomanr.lumo.multiplatform.ui.components.Text

@Composable
fun TestSample() {
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("A", "B", "C")
    var selectedOption by remember { mutableStateOf(options[0]) }

    Scaffold(
        topBar = {
            SampleScreenTopBar(title = "Accordion")
        },
    ) { paddingValues ->


        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).verticalScroll(rememberScrollState()),
        ) {

            Row {
                Box(
                    modifier = Modifier.clickable { expanded = true }.padding(16.dp).border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                        .padding(8.dp),
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

                Spacer(modifier = Modifier.height(16.dp))
                Test()
            }

            repeat(10) { key ->
                key(key) {
                    Column {
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier.background(AppTheme.colors.success).fillMaxWidth().height(60.dp),
                        )
                    }
                }
            }
        }
    }
}