package com.nomanr.composeui.plugin.configs

import com.nomanr.composeui.exceptions.ComposeUIException
import com.nomanr.composeui.utils.LinkFormatter
import com.nomanr.composeui.utils.Logger
import java.io.File
import java.util.Properties
import org.gradle.api.Project

class PropertyLoader(private val project: Project, private val logger: Logger = Logger.getInstance()) {

    fun loadProperties(): ComposeUIConfig {
        val propertiesFile = File(project.rootDir, COMPOSE_UI_PROPERTIES)

        if (!propertiesFile.exists()) {
            throw ComposeUIException("The plugin is not setup. Run the plugin with --init to get started.")
        }

        val properties = Properties()
        propertiesFile.inputStream().use { inputStream ->
            properties.load(inputStream)
        }

        val requiredProperties = setOf(PROPERTY_THEME_NAME, PROPERTY_COMPONENTS_DIR, PROPERTY_PACKAGE_NAME)
        if (!properties.stringPropertyNames().containsAll(requiredProperties)) {
            throw ComposeUIException("Missing required configs in $COMPOSE_UI_PROPERTIES. Expected: $requiredProperties.")
        }

        return ComposeUIConfig(
            themeName = properties.getProperty(PROPERTY_THEME_NAME).orEmpty(),
            componentsDir = properties.getProperty(PROPERTY_COMPONENTS_DIR).orEmpty(),
            packageName = properties.getProperty(PROPERTY_PACKAGE_NAME).orEmpty()
        )
    }

    fun createDefaultPropertiesFile() {
        val propertiesFile = File(project.rootDir, COMPOSE_UI_PROPERTIES)

        if (propertiesFile.exists()) {
            throw ComposeUIException("Compose UI plugin is already initialised. The config file can be found here: ${configFilePath()}")
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
        logger.success("Default config file created at: ${configFilePath()}")
    }

    private fun configFilePath(): String {
        return LinkFormatter.formatLink(rootDir = project.rootDir, File(COMPOSE_UI_PROPERTIES))
    }

    companion object {
        private const val COMPOSE_UI_PROPERTIES = "compose-ui.properties"
        private const val PROPERTY_THEME_NAME = "ThemeName"
        private const val PROPERTY_COMPONENTS_DIR = "ComponentsDir"
        private const val PROPERTY_PACKAGE_NAME = "PackageName"
    }
}
