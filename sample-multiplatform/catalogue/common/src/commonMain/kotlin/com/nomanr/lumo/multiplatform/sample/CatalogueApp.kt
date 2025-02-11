package com.nomanr.lumo.multiplatform.sample

import androidx.compose.runtime.Composable
import com.nomanr.lumo.multiplatform.sample.navigation.CatalogueAppNavHost
import com.nomanr.lumo.multiplatform.ui.components.Scaffold

@Composable
fun CatalogueApp(
    appState: CatalogueAppState = rememberCatalogueAppState(),
) {
    CatalogueAppProviders {
        Scaffold {
            CatalogueAppNavHost(
                appState = appState,
            )
        }
    }
}
