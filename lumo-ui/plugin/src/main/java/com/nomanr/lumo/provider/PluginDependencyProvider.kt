package com.nomanr.lumo.provider

import com.nomanr.lumo.utils.Logger
import org.gradle.api.Project

class PluginDependencyProvider(private val logger: Logger = Logger.getInstance()) {

    private val composeBomVersion = "2024.12.01"
    private val composeVersion = "1.5.5"
    private val rippleVersion = "1.7.6"
    private val nomanrComposablesVersion = "0.0.1-alpha.12"

    private fun getComposeBom(): String {
        return "androidx.compose:compose-bom:$composeBomVersion"
    }

    private fun getComposeDependencies(): List<String> {
        return listOf(
            "androidx.compose.foundation:foundation:$composeVersion",
            "androidx.compose.foundation:foundation-layout:$composeVersion",
            "androidx.compose.ui:ui:$composeVersion",
            "androidx.compose.ui:ui-tooling:$composeVersion",
            "androidx.compose.ui:ui-tooling-preview:$composeVersion",
            "androidx.compose.ui:ui-util:$composeVersion"
        )
    }

    private fun getSupportingDependencies(): List<String> {
        return listOf(
            "androidx.compose.material:material-ripple:$rippleVersion"
        )
    }

    fun printFormattedComposeDependencies() {
        val dependencies = mutableListOf(
            "api(platform(\"${getComposeBom()}\"))"
        )

        val allDependencies = listOf(
            *getComposeDependencies().toTypedArray(),
            *getSupportingDependencies().toTypedArray()
        )

        allDependencies.forEach { dependencies.add("api(\"$it\")") }
        val formattedDependencies = dependencies.joinToString("\n").trimIndent()

        logger.blankLine()
        logger.info("Add the following dependencies to your build.gradle.kts file:")
        logger.info(formattedDependencies)
    }

    fun getNomanRDependencyMessage(): String {
        val dependency = "com.nomanr:composables"
        return "Note: Add the following dependency to the project to use this component: \n" +
                "implementation(\"${dependency}:${nomanrComposablesVersion}\")\n" +
                "Reference: https://github.com/nomanr/compose-components"
    }
}