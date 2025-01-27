
package com.nomanr.lumo.multiplatform.ui.foundation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable

val WindowInsets.Companion.systemBarsForVisualComponents: WindowInsets
    @Composable
    get() = systemBars
