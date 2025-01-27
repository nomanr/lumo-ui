package com.nomanr.lumo.multiplatform.sample

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberCatalogueAppState(
    navController: NavHostController = rememberNavController(),
): CatalogueAppState {
    return CatalogueAppState(
        navController = navController,
    )
}

@Stable
class CatalogueAppState(
    val navController: NavHostController,
)
