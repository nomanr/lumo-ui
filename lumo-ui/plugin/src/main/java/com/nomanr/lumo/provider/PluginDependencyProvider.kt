package com.nomanr.lumo.provider

import com.nomanr.lumo.utils.Logger
import org.gradle.api.Project

class PluginDependencyProvider(private val logger: Logger = Logger.getInstance()) {

    private val composeBomVersion = "2024.12.01"
    private val rippleVersion = "1.7.6"

    private fun getComposeBom(): String {
        return "androidx.compose:compose-bom:$composeBomVersion"
    }

    private fun getComposeDependencies(): List<String> {
        return listOf(
            "androidx.compose.foundation:foundation",
            "androidx.compose.foundation:foundation-layout",
            "androidx.compose.ui:ui",
            "androidx.compose.ui:ui-tooling",
            "androidx.compose.ui:ui-tooling-preview",
            "androidx.compose.ui:ui-util"
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
}