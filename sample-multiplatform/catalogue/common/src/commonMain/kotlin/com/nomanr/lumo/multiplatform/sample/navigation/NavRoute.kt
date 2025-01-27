package com.nomanr.lumo.multiplatform.sample.navigation

import com.nomanr.sample.ui.sample.ComponentId
import kotlinx.serialization.Serializable

sealed class NavRoute {
    @Serializable
    data object Home : NavRoute()

    @Serializable
    data class Demo(val componentId: ComponentId) : NavRoute()

    @Serializable
    data object UpdateTheme : NavRoute()
}
