package com.nomanr.sample.ui.navigation

import com.nomanr.sample.ui.sample.ComponentId
import kotlinx.serialization.Serializable


sealed class  NavRoute {
    @Serializable
    data object Home: NavRoute()

    @Serializable
    data class Demo(val componentId: ComponentId) : NavRoute()
}