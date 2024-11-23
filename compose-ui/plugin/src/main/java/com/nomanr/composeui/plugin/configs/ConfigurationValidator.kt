package com.nomanr.composeui.plugin.configs

import com.nomanr.composeui.utils.Logger
import java.io.File
import org.gradle.api.Project

class ConfigurationValidator(private val project: Project, private val logger: Logger) {

    fun validate(config: ComposeUIConfig): Boolean {
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
