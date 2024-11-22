package com.nomanr.composeui.actions

import com.nomanr.composeui.configs.PropertyLoader
import com.nomanr.composeui.configs.ConfigurationValidator
import com.nomanr.composeui.provider.ComposeDependencyProvider
import com.nomanr.composeui.utils.Logger
import org.gradle.api.Project

class Initialiser(project: Project) {

    private val logger = Logger.getInstance()
    private val propertyLoader = PropertyLoader(project, logger)
    private val validator = ConfigurationValidator(project, logger)
    private val composeDependencyProvider = ComposeDependencyProvider(project)

    fun init() {
        try {
            propertyLoader.createDefaultPropertiesFile()
            logger.success("Default properties file created successfully.")

            composeDependencyProvider.printFormattedDependencies()
        } catch (e: IllegalStateException) {
            logger.error(e.message ?: "Failed to create default properties file.")
        }
    }

    fun validateConfigs(): Boolean {
        return try {
            val config = propertyLoader.loadProperties()
            if (validator.validate(config)) {
                logger.success("Configuration is valid.")
                true
            } else {
                logger.error("Configuration validation failed.")
                false
            }
        } catch (e: Exception) {
            logger.error(e.message ?: "An error occurred during configuration validation.")
            false
        }
    }
}