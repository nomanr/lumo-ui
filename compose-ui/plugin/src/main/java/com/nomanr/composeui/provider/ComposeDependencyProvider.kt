package com.nomanr.composeui.provider

import com.nomanr.composeui.utils.Logger
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension

class ComposeDependencyProvider(project: Project, private val logger: Logger = Logger.getInstance()) {

    private val versionCatalog = project.extensions.getByType(VersionCatalogsExtension::class.java).named("libs")

    private fun getComposeBom(): String {
        return versionCatalog.findLibrary("androidx-compose-bom")
            .orElseThrow { IllegalArgumentException("androidx-compose-bom not found in libs.versions.toml") }
            .get()
            .toString()
    }

    private fun getComposeDependencies(): List<String> {
        val dependencies = listOf(
            "androidx-compose-foundation",
            "androidx-compose-foundation-layout",
            "androidx-compose-ui",
            "androidx-compose-ui-tooling",
            "androidx-compose-ui-tooling-preview",
            "androidx-compose-ui-util"
        )

        return dependencies.map { dependency ->
            versionCatalog.findLibrary(dependency)
                .orElseThrow { IllegalArgumentException("$dependency not found in libs.versions.toml") }
                .get()
                .toString()
        }
    }

    private fun getSupportingDependencies(): List<String> {
        val dependencies = listOf("androidx-compose-ripple")

        return dependencies.map { dependency ->
            versionCatalog.findLibrary(dependency)
                .orElseThrow { IllegalArgumentException("$dependency not found in libs.versions.toml") }
                .get()
                .toString()
        }
    }

    fun printFormattedDependencies() {
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
