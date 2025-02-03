package com.nomanr.lumo.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
internal actual fun windowContainerWidthInPx(): Int {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    return with(density) {
        configuration.screenWidthDp.dp.roundToPx()

    }
}