package com.nomanr.lumo.multiplatform.sample.navigation

import com.nomanr.lumo.multiplatform.sample.sample.ComponentId
import kotlinx.serialization.Serializable

sealed class NavRoute {
    @Serializable
    data object Home : NavRoute()

    @Serializable
    data class Demo(val componentId: ComponentId) : NavRoute()

    @Serializable
    data object UpdateTheme : NavRoute()
}
