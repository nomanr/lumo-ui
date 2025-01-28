package com.nomanr.lumo.multiplatform.sample.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.nomanr.lumo.multiplatform.sample.CatalogueAppState
import com.nomanr.lumo.multiplatform.sample.home.HomeScreen
import com.nomanr.lumo.multiplatform.sample.sample.samples.SampleScreen

@Composable
fun CatalogueAppNavHost(
    startDestination: NavRoute = NavRoute.Home,
    appState: CatalogueAppState,
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
                },
            )
        }

        composable<NavRoute.Demo> {
            val args = it.toRoute<NavRoute.Demo>()
            SampleScreen(componentId = args.componentId, navigateUp = { navController.navigateUp() })
        }

        //TODO: Add Modal Bottom Sheet first
//        composable<NavRoute.UpdateTheme> {
//            UpdateThemeScreen(navigateUp = { navController.navigateUp() })
//        }
    }
}
