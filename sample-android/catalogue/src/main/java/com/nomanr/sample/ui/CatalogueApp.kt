package com.nomanr.sample.ui

import androidx.compose.runtime.Composable
import com.nomanr.sample.ui.components.Scaffold
import com.nomanr.sample.ui.navigation.CatalogueAppNavHost

@Composable
fun CatalogueApp(
    appState: CatalogueAppState = rememberCatalogueAppState(),
) {
    Scaffold {
        CatalogueAppNavHost(
            appState = appState,
        )
    }
}
