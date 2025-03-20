package com.nomanr.lumo.multiplatform.sample

import androidx.compose.runtime.Composable

@Composable
fun CatalogueApp(
    appState: CatalogueAppState = rememberCatalogueAppState(),
) {
    CatalogueAppProviders {
//        Scaffold {
//            CatalogueAppNavHost(
//                appState = appState,
//            )
//        }

        TestSample()
    }
}
