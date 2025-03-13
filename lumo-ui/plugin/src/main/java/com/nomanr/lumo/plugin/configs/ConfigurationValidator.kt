package com.nomanr.lumo.plugin.configs

import com.nomanr.lumo.utils.Logger
import org.gradle.api.Project
import java.io.File

class ConfigurationValidator(private val project: Project, private val logger: Logger) {
    fun validate(config: LumoConfig): Boolean {
        val destinationDir = File(project.rootDir, config.componentsDir)

        if (!destinationDir.exists()) {
            logger.error("The components directory (${config.componentsDir}) does not exist.")
            return false
        }

        val normalizedDirPath =
            config.componentsDir
                .removeSuffix("/").removeSuffix("\\") // Remove the last slash or backslash
                .replace("\\", ".") // Replace Windows-style backslashes
                .replace("/", ".") // Replace Unix-style forward slashes
                .replace("//", ".") // Handle double forward slashes (if any)

        val normalizedPackageName =
            config.packageName.normalizePackageName()

        if (!normalizedDirPath.endsWith(normalizedPackageName)) {
            logger.warn("componentsDir: ${config.componentsDir}")
            logger.warn("normalisedComponentsDir: $normalizedDirPath")
            logger.warn("config.packageName: ${config.packageName}")
            logger.warn("normalizedPackageName: $normalizedPackageName")

            logger.warn("The directory ($normalizedDirPath) and the package name ($normalizedPackageName) do not match.")
            return false
        }

        return true
    }
}

private fun String.normalizePackageName(): String = let { packageName ->
    val splitIndices = buildList {
        var opened = false
        packageName.forEachIndexed { i, c ->
            when (c) {
                '.' -> if (!opened) add(i)
                '`' -> opened = !opened
            }
        }
    }
    buildList {
        var from = 0
        for (splitAt in splitIndices) {
            add(packageName.substring(from, splitAt).removeSurrounding("`"))
            from = splitAt + 1
        }
        if (from <= packageName.length) {
            add(packageName.substring(from, packageName.length).removeSurrounding("`"))
        }
    }.joinToString(".")
}
