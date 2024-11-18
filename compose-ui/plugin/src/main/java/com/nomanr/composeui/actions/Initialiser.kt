package com.nomanr.composeui.actions

import com.nomanr.composeui.plugin.utils.Logger
import org.gradle.api.Project
import java.io.File
import java.util.Properties

class Initialiser(
    private val project: Project
) {

    lateinit var config: ComposeUIConfig

    init {
        loadProperties()
    }


    fun init() {
        val initFile = File(project.rootDir, COMPOSE_UI_PROPERTIES)
        if (initFile.exists()) {
            Logger.error("Compose UI Plugin already initialized!")
            Logger.info("The properties file can be found here: ${getFilePath()}")
            return
        }

        initFile.createNewFile()

        initFile.writeText(defaultProperties)
        Logger.success("Created the config file successfully: ${getFilePath()}")
        Logger.info("Next step: Open the file and set the correct directory for ui-components.")
        Logger.info("Tip: Its best to have a separate module e.g. 'ui-components' that holds all the components.")

    }

    private fun loadProperties() {
        val propertiesFile = File(project.rootDir, COMPOSE_UI_PROPERTIES)
        if (!propertiesFile.exists()) {
            return
        }

        val properties = Properties()

        propertiesFile.inputStream().use { inputStream ->
            properties.load(inputStream)
        }

        val propertiesName = properties.stringPropertyNames()

        if (!propertiesName.containsAll(SUPPORTED_PROPERTIES)) {
            Logger.error(
                "$COMPOSE_UI_PROPERTIES does not contain all the configurations. " + "Make sure it includes all the required configurations i.e. ${SUPPORTED_PROPERTIES.joinToString()}"
            )
            return
        }

        config = ComposeUIConfig(
            themeName = properties.getProperty(PROPERTY_THEME_NAME),
            componentsDir = properties.getProperty(PROPERTY_COMPONENTS_DIR)
        )

    }

    private fun getFilePath(): String {
        return "file:///${project.rootDir.path}/${COMPOSE_UI_PROPERTIES}"
    }


    companion object {
        const val COMPOSE_UI_PROPERTIES = "compose-ui.properties"
        const val PROPERTY_THEME_NAME = "ThemeName"
        const val PROPERTY_COMPONENTS_DIR = "ComponentsDir"
        val SUPPORTED_PROPERTIES = setOf(PROPERTY_THEME_NAME, PROPERTY_COMPONENTS_DIR)

        val defaultProperties = """
            # Compose UI Plugin
            # This file is used to store configurations for the Compose UI Plugin
            # Do not delete this file
            
            ThemeName=AppTheme
            ComponentsDir=<<path-to-components>>
            """.trimIndent()
    }
}

data class ComposeUIConfig(
    val themeName: String, val componentsDir: String
)

