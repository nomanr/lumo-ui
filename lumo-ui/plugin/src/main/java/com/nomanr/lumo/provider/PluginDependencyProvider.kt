package com.nomanr.lumo.provider

import com.nomanr.lumo.utils.Logger

class PluginDependencyProvider(
    private val logger: Logger = Logger.getInstance()
) {
    private val composeBomVersion = "2024.12.01"
    private val rippleVersion = "1.7.6"

    private enum class DependencyType {
        ANDROID, MULTIPLATFORM
    }

    private fun composeBom() = "androidx.compose:compose-bom:$composeBomVersion"

    private val composeAndroidDependencies = listOf(
        "androidx.compose.foundation:foundation",
        "androidx.compose.foundation:foundation-layout",
        "androidx.compose.ui:ui",
        "androidx.compose.ui:ui-tooling",
        "androidx.compose.ui:ui-tooling-preview",
        "androidx.compose.ui:ui-util"
    )

    private val composeMultiplatformDependencies = listOf(
        "compose.runtime",
        "compose.foundation",
        "compose.material3",
        "compose.ui"
    )

    private val supportingDependencies = listOf(
        "androidx.compose.material:material-ripple:$rippleVersion"
    )

    private fun getDependenciesFor(type: DependencyType): List<String> {
        return when (type) {
            DependencyType.ANDROID -> listOf("api(platform(\"${composeBom()}\"))") +
                    composeAndroidDependencies.map { "api(\"$it\")" } +
                    supportingDependencies.map { "api(\"$it\")" }

            DependencyType.MULTIPLATFORM ->
                composeMultiplatformDependencies.map { "api(\"$it\")" }
        }
    }

    private fun printDependencies(title: String, dependencies: List<String>) {
        logger.blankLine()
        logger.info(title)
        logger.info(dependencies.joinToString("\n"))
        logger.info("Note: Ignore this message if you have already added the dependencies.")
    }

    private fun printFormattedComposeAndroidDependencies() {
        printDependencies(
            "The plugin requires the following dependencies to your build.gradle.kts file:",
            getDependenciesFor(DependencyType.ANDROID)
        )
    }

    private fun printFormattedComposeMultiplatformDependencies() {
        printDependencies(
            "The plugin requires the following dependencies to your build.gradle.kts file:",
            getDependenciesFor(DependencyType.MULTIPLATFORM)
        )
    }

    fun printFormattedDependencies(isKotlinMultiplatform: Boolean) {
        if (isKotlinMultiplatform) {
            printFormattedComposeMultiplatformDependencies()
        } else {
            printFormattedComposeAndroidDependencies()
        }

    }

}