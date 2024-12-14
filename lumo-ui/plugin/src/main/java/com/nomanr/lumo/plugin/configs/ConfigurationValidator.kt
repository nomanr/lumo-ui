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

        val packageNamePath = config.packageName.replace(".", File.separator)
        if (!config.componentsDir.endsWith(packageNamePath)) {
            logger.warn("The directory (${config.componentsDir}) and the package name (${config.packageName}) do not match.")
            return false
        }
        return true
    }
}
