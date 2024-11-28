package com.nomanr.sample.ui.navigation

import kotlinx.serialization.Serializable


sealed class  NavRoute {
    @Serializable
    data object Home: NavRoute()

    @Serializable
    data class Demo(val component: String) : NavRoute()
}