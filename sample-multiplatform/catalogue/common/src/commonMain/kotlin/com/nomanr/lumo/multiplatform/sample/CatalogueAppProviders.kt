package com.nomanr.lumo.multiplatform.sample

import androidx.compose.runtime.Composable
import com.nomanr.lumo.multiplatform.ui.AppTheme

@Composable
fun CatalogueAppProviders(
    app: @Composable () -> Unit,
) {
    AppTheme {
        app()
    }
}
