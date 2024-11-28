package com.nomanr.sample.ui.demo

import androidx.compose.runtime.Composable
import com.nomanr.sample.ui.components.Text

@Composable
fun DemoScreen(component: String) {
    Text(text = "Demo Screen - $component")
}