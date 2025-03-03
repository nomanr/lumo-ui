package com.nomanr.lumo.multiplatform.sample.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.nomanr.lumo.multiplatform.sample.CatalogueAppState
import com.nomanr.lumo.multiplatform.sample.home.HomeScreen
import com.nomanr.lumo.multiplatform.sample.sample.ComponentId
import com.nomanr.lumo.multiplatform.sample.sample.samples.SampleScreen

@Composable
fun CatalogueAppNavHost(
    startDestination: NavRoute = NavRoute.Home,
    appState: CatalogueAppState,
) {
    val navController = appState.navController

    LaunchedEffect(Unit) {
        val initialComponentId = getInitialComponentId()
        if (initialComponentId != null) {
            ComponentId.entries.find { it.name == initialComponentId }?.let {
                navController.navigate(NavRoute.Demo(it))
            }
        }
    }

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

        composable<NavRoute.Demo>(
            typeMap = componentIdNavTypeMap,
        ) {
            val args = it.toRoute<NavRoute.Demo>()
            SampleScreen(componentId = args.componentId, navigateUp = { navController.navigateUp() })
        }

        // TODO: Add Modal Bottom Sheet first
//        composable<NavRoute.UpdateTheme> {
//            UpdateThemeScreen(navigateUp = { navController.navigateUp() })
//        }
    }
}

expect fun getInitialComponentId(): String?
