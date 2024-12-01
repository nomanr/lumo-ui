package com.nomanr.sample.ui.navigation

import com.nomanr.sample.ui.data.Component
import kotlinx.serialization.Serializable


sealed class  NavRoute {
    @Serializable
    data object Home: NavRoute()

    @Serializable
    data class Demo(val component: Component) : NavRoute()
}