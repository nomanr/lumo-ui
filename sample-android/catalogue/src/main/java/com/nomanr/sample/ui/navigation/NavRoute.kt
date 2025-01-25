package com.nomanr.sample.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoute {
    @Serializable
    data object Home: NavRoute()

    @Serializable
    data class Demo(val componentIdString: String) : NavRoute()

    @Serializable
    data object UpdateTheme: NavRoute()
}