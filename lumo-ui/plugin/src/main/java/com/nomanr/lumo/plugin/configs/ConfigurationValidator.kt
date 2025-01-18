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

        val normalizedDirPath = config.componentsDir
            .replace("\\", ".") // Replace Windows-style backslashes
            .replace("/", ".")  // Replace Unix-style forward slashes
            .replace("//", ".") // Handle double forward slashes (if any)
            .removeSuffix("/").removeSuffix("\\") // Remove last slash



        if (!normalizedDirPath.endsWith(config.packageName)) {
            logger.warn("componentsDir: $config.componentsDir")
            logger.warn("normalisedComponentsDir: $normalizedDirPath")
            logger.warn("config.packageName: ${config.packageName}")

            logger.warn("The directory (${normalizedDirPath}) and the package name (${config.packageName}) do not match.")
            return false
        }

        return true
    }
}
