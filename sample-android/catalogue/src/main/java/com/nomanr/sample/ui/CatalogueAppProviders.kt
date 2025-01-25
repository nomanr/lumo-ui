package com.nomanr.sample.ui

import androidx.compose.runtime.Composable

@Composable
fun CatalogueAppProviders(
    app: @Composable () -> Unit,
) {
    AppTheme {
        app()
    }
}
