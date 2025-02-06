package com.nomanr.lumo.plugin

import com.nomanr.lumo.exceptions.LumoException
import com.nomanr.lumo.plugin.actions.GenerateComponent
import com.nomanr.lumo.plugin.actions.Initialiser
import com.nomanr.lumo.plugin.configs.PropertyLoader
import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents
import com.nomanr.lumo.provider.PluginDependencyProvider
import com.nomanr.lumo.utils.Logger
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option

abstract class LumoTask : DefaultTask() {
    @set:Option(option = "init", description = "Initialize Lumo UI Plugin")
    @get:Input
    var init: Boolean = false

    @set:Option(option = "setup", description = "Setup theme to get started and verify the configs")
    @get:Input
    var setup: Boolean = false

    @set:Option(
        option = "required-deps",
        description = "Returns the required dependencies to be added to the build.gradle.kts file",
    )
    @get:Input
    var requiredDeps: Boolean = false

    @set:Option(option = "plugin-help", description = "Display help message for Lumo UI Plugin")
    @get:Input
    var help: Boolean = false

    @set:Option(option = "add", description = "Add a new Lumo UI Component")
    @get:Input
    @Optional
    var componentToAdd: String? = null

    @set:Option(option = "add-all", description = "Add all Lumo UI Components")
    @get:Input
    var addAll: Boolean = false

    @set:Option(option = "available-components", description = "List all available components")
    @get:Input
    var availableComponents: Boolean = false


    private val propertyLoader by lazy { PropertyLoader(project) }
    private val initialiser by lazy { Initialiser(project, propertyLoader) }
    private val dependencyProvider by lazy { PluginDependencyProvider() }
    private val generateComponent by lazy { GenerateComponent(project, propertyLoader) }
    private val logger = Logger.getInstance()

    @TaskAction
    fun execute() {
        if (help) {
            printHelpMessage()
            return
        }
        if (noInputProvided()) {
            throw LumoException("No input provided, run with --plugin-help for more information")
        }

        if (requiredDeps) {
            dependencyProvider.printFormattedComposeDependencies()
            return
        }

        if(availableComponents) {
            generateComponent.printAllAvailableComponents()
            return
        }

        if (init) {
            initialiser.init()
            return
        }

        if (!initialiser.validateConfigs()) {
            return
        }

        if (setup) {
            generateComponent.execute(SupportedComponents.Theme)
            return
        }

        if (componentToAdd != null) {
            generateComponent.execute(componentToAdd!!)
        }

        if (addAll) {
            generateComponent.executeAll()
        }
    }

    private fun noInputProvided(): Boolean {
        return inputs.properties.all { property ->
            when (val value = property.value) {
                is Boolean -> !value
                is String? -> value.isNullOrEmpty()
                else -> false
            }
        }
    }

    private fun printHelpMessage() {
        val helpMessage =
            """
            |Usage: ./gradlew lumo --option <value>
            |
            |Options:
            |  --init                  Initialize Lumo UI Plugin
            |  --setup                 Setup theme to get started and verify the configs
            |  --required-deps         Returns the required dependencies to be added to the build.gradle.kts file
            |  --add <component>       Add a new Lumo UI Component
            |  --plugin-help           Display this help message
            |
            """.trimMargin()

        logger.info(helpMessage)
    }
}
