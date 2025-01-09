package com.nomanr.lumo.plugin.configs

import com.nomanr.lumo.exceptions.LumoException
import com.nomanr.lumo.utils.LinkFormatter
import com.nomanr.lumo.utils.Logger
import org.gradle.api.Project
import java.io.File
import java.util.Properties

class PropertyLoader(private val project: Project, private val logger: Logger = Logger.getInstance()) {

    fun loadProperties(): LumoConfig {
        val propertiesFile = File(project.rootDir, LUMO_PROPERTIES)

        if (!propertiesFile.exists()) {
            throw LumoException("The plugin is not setup. Run the plugin with --init to get started.")
        }

        val properties = Properties()
        propertiesFile.inputStream().use { inputStream ->
            properties.load(inputStream)
        }

        val requiredProperties = setOf(PROPERTY_THEME_NAME, PROPERTY_COMPONENTS_DIR, PROPERTY_PACKAGE_NAME)
        if (!properties.stringPropertyNames().containsAll(requiredProperties)) {
            throw LumoException("Missing required configs in $LUMO_PROPERTIES. Expected: $requiredProperties.")
        }

        return LumoConfig(
            themeName = properties.getProperty(PROPERTY_THEME_NAME).orEmpty(),
            componentsDir = properties.getProperty(PROPERTY_COMPONENTS_DIR).orEmpty(),
            packageName = properties.getProperty(PROPERTY_PACKAGE_NAME).orEmpty()
        )
    }

    fun createDefaultPropertiesFile() {
        val propertiesFile = File(project.rootDir, LUMO_PROPERTIES)

        if (propertiesFile.exists()) {
            throw LumoException("Lumo UI plugin is already initialised. The config file can be found here: ${configFilePath()}")
        }

        val defaultProperties = """
            # Lumo UI Plugin
            # This file is used to store configurations for the Lumo UI Plugin
            # Do not delete this file
            
            ThemeName=AppTheme
            ComponentsDir=<<relative-path-to-components-dir-from-root>>
            PackageName=<<component-files-package-name>>
        """.trimIndent()

        propertiesFile.writeText(defaultProperties)
        logger.success("Default config file created at: ${configFilePath()}")
    }

    fun hasPropertiesFile(): Boolean {
        return File(project.rootDir, LUMO_PROPERTIES).exists()
    }

    fun configFilePath(): String {
        return LinkFormatter.formatLink(rootDir = project.rootDir, File(LUMO_PROPERTIES))
    }

    companion object {
        private const val LUMO_PROPERTIES = "lumo.properties"
        private const val PROPERTY_THEME_NAME = "ThemeName"
        private const val PROPERTY_COMPONENTS_DIR = "ComponentsDir"
        private const val PROPERTY_PACKAGE_NAME = "PackageName"
    }
}
