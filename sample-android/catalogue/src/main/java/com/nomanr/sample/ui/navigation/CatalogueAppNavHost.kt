package com.nomanr.sample.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.nomanr.sample.ui.CatalogueAppState
import com.nomanr.sample.ui.home.HomeScreen
import com.nomanr.sample.ui.sample.SampleScreen
import com.nomanr.sample.ui.update_theme.UpdateThemeScreen

@Composable
fun CatalogueAppNavHost(
    startDestination: NavRoute = NavRoute.Home, appState: CatalogueAppState
) {

    val navController = appState.navController

    NavHost(navController = navController, startDestination = startDestination) {
        composable<NavRoute.Home> {
            HomeScreen(
                navigateToDemo = { component ->
                    navController.navigate(NavRoute.Demo(component.id))
                },
                navigateToUpdateTheme = {
                    navController.navigate(NavRoute.UpdateTheme)
                }
            )
        }

        composable<NavRoute.Demo> {
            val args = it.toRoute<NavRoute.Demo>()
            SampleScreen(componentId = args.componentId, navigateUp = { navController.navigateUp() })
        }

        composable<NavRoute.UpdateTheme>{
            UpdateThemeScreen(navigateUp = { navController.navigateUp() })
        }
    }
}