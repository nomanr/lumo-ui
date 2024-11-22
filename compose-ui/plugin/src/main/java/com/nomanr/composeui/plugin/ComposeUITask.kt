package com.nomanr.composeui.plugin

import com.nomanr.composeui.actions.Initialiser
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

    @set:Option(option = "required-deps", description = "Returns the required dependencies to be added to the build.gradle.kts file")
    @get:Input
    var requiredDeps: Boolean = false

    @set:Option(option = "add", description = "Add a new Compose UI Component")
    @get:Input
    @Optional
    var componentToAdd: String? = null

    private val initialiser = Initialiser(project)
    private val dependencyProvider = ComposeDependencyProvider(project)
    private val logger = Logger.getInstance()

    @TaskAction
    fun execute() {
        if (noInputProvided()) {
            // TODO: Add a descriptive message and perhaps return all available options
            logger.error("No options provided!")
            return
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
            logger.info(">>SETTING UP")
            return
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
}
