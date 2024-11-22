package com.nomanr.composeui.configs

import com.nomanr.composeui.utils.Logger
import java.io.File
import java.util.Properties
import org.gradle.api.Project

class PropertyLoader(private val project: Project, private val logger: Logger) {

    fun loadProperties(): ComposeUIConfig {
        val propertiesFile = File(project.rootDir, COMPOSE_UI_PROPERTIES)

        if (!propertiesFile.exists()) {
            logger.error("Compose UI plugin is not setup. Please run the plugin with --init to get started.")
            throw IllegalStateException("Config file not found.")
        }

        val properties = Properties()
        propertiesFile.inputStream().use { inputStream ->
            properties.load(inputStream)
        }

        val requiredProperties = setOf(PROPERTY_THEME_NAME, PROPERTY_COMPONENTS_DIR, PROPERTY_PACKAGE_NAME)
        if (!properties.stringPropertyNames().containsAll(requiredProperties)) {
            logger.error("Missing required configs in $COMPOSE_UI_PROPERTIES. Expected: $requiredProperties.")
            throw IllegalArgumentException("Missing required properties.")
        }

        logger.success("Properties loaded successfully.")
        return ComposeUIConfig(
            themeName = properties.getProperty(PROPERTY_THEME_NAME).orEmpty(),
            componentsDir = properties.getProperty(PROPERTY_COMPONENTS_DIR).orEmpty(),
            packageName = properties.getProperty(PROPERTY_PACKAGE_NAME).orEmpty()
        )
    }

    fun createDefaultPropertiesFile() {
        val propertiesFile = File(project.rootDir, COMPOSE_UI_PROPERTIES)

        if (propertiesFile.exists()) {
            throw IllegalStateException("Compose UI plugin is already initialised. The config file can be found here: ${configFilePath()}")
        }

        val defaultProperties = """
            # Compose UI Plugin
            # This file is used to store configurations for the Compose UI Plugin
            # Do not delete this file
            
            ThemeName=AppTheme
            ComponentsDir=<<path-to-components>>
            PackageName=<<component-files-package-name>>
        """.trimIndent()

        propertiesFile.writeText(defaultProperties)
        logger.success("Default properties file created at: ${configFilePath()}")
    }

    private fun configFilePath(): String {
        return "file:///${project.rootDir}/$COMPOSE_UI_PROPERTIES"
    }

    companion object {
        private const val COMPOSE_UI_PROPERTIES = "compose-ui.properties"
        private const val PROPERTY_THEME_NAME = "ThemeName"
        private const val PROPERTY_COMPONENTS_DIR = "ComponentsDir"
        private const val PROPERTY_PACKAGE_NAME = "PackageName"
    }
}