package com.nomanr.lumo.plugin.actions

import com.nomanr.lumo.plugin.configs.ConfigurationValidator
import com.nomanr.lumo.plugin.configs.PropertyLoader
import com.nomanr.lumo.provider.PluginDependencyProvider
import com.nomanr.lumo.utils.Logger
import org.gradle.api.Project

class Initialiser(project: Project, private val propertyLoader: PropertyLoader) {

    private val logger = Logger.getInstance()
    private val validator = ConfigurationValidator(project, logger)
    private val pluginDependencyProvider = PluginDependencyProvider(project)

    fun init() {
        try {
            propertyLoader.createDefaultPropertiesFile()
            logger.success("Default properties file created successfully.")

            pluginDependencyProvider.printFormattedComposeDependencies()
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