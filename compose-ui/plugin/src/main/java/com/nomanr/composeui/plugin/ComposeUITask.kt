package com.nomanr.composeui.plugin

import com.nomanr.composeui.exceptions.ComposeUIException
import com.nomanr.composeui.plugin.actions.GenerateComponent
import com.nomanr.composeui.plugin.actions.Initialiser
import com.nomanr.composeui.plugin.configs.PropertyLoader
import com.nomanr.composeui.plugin.template.SupportedComponents
import com.nomanr.composeui.provider.ComposeDependencyProvider
import com.nomanr.composeui.utils.Logger
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option

abstract class ComposeUITask : DefaultTask() {
    @set:Option(option = "init", description = "Initialize Compose UI Plugin")
    @get:Input
    var init: Boolean = false

    @set:Option(option = "setup", description = "Setup theme to get started and verify the configs")
    @get:Input
    var setup: Boolean = false

    @set:Option(
        option = "required-deps",
        description = "Returns the required dependencies to be added to the build.gradle.kts file"
    )
    @get:Input
    var requiredDeps: Boolean = false

    @set:Option(option = "plugin-help", description = "Display help message for Compose UI Plugin")
    @get:Input
    var help: Boolean = false

    @set:Option(option = "add", description = "Add a new Compose UI Component")
    @get:Input
    @Optional
    var componentToAdd: String? = null

    private val propertyLoader by lazy { PropertyLoader(project) }
    private val initialiser by lazy { Initialiser(project, propertyLoader) }
    private val dependencyProvider by lazy { ComposeDependencyProvider(project) }
    private val generateComponent by lazy { GenerateComponent(project, propertyLoader) }
    private val logger = Logger.getInstance()

    @TaskAction
    fun execute() {
        if (help) {
            printHelpMessage()
            return
        }
        if (noInputProvided()) {
            throw ComposeUIException("No input provided, run with --plugin-help for more information")
        }

        if (requiredDeps) {
            dependencyProvider.printFormattedDependencies()
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
        val helpMessage = """
            |Usage: ./gradlew composeUI --option <value>
            |
            |Options:
            |  --init                  Initialize Compose UI Plugin
            |  --setup                 Setup theme to get started and verify the configs
            |  --required-deps         Returns the required dependencies to be added to the build.gradle.kts file
            |  --add <component>       Add a new Compose UI Component
            |  --plugin-help           Display this help message
            |
        """.trimMargin()

        logger.info(helpMessage)
    }
}

