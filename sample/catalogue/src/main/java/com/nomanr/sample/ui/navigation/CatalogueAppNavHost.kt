package com.nomanr.sample.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nomanr.sample.ui.CatalogueAppState
import com.nomanr.sample.ui.demo.DemoScreen
import com.nomanr.sample.ui.home.HomeScreen

@Composable
fun CatalogueAppNavHost(
    startDestination: NavRoute = NavRoute.HOME, appState: CatalogueAppState
) {

    val navController = appState.navController

    NavHost(navController = navController, startDestination = startDestination.name) {
        composable(
            route = NavRoute.HOME.name
        ) {
            HomeScreen()
        }

        composable(
            route = NavRoute.Demo.name
        ) {
            DemoScreen()
        }
    }


}