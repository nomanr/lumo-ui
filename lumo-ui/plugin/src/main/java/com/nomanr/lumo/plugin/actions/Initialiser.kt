package com.nomanr.lumo.plugin.actions

import com.nomanr.lumo.plugin.configs.ConfigurationValidator
import com.nomanr.lumo.plugin.configs.PropertyLoader
import com.nomanr.lumo.utils.Logger
import org.gradle.api.Project

class Initialiser(project: Project, private val propertyLoader: PropertyLoader) {
    private val logger = Logger.getInstance()
    private val validator = ConfigurationValidator(project, logger)

    fun init() {
        try {
            if (propertyLoader.hasPropertiesFile()) {
                logger.error("Lumo UI plugin is already initialised. The config file can be found here: ${propertyLoader.configFilePath()}")
                return
            }
            propertyLoader.createDefaultPropertiesFile()
            logger.success("Initialised successfully. List of required dependencies printed above.")
        } catch (e: IllegalStateException) {
            logger.error(e.message ?: "Failed to create default properties file.")
        }
    }

    fun validateConfigs(): Boolean {
        return try {
            val config = propertyLoader.loadProperties()
            return validator.validate(config)
        } catch (e: Exception) {
            logger.error(e.message ?: "An error occurred during configuration validation.")
            false
        }
    }
}
