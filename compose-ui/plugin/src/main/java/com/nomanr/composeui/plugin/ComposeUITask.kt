package com.nomanr.composeui.plugin

import com.nomanr.composeui.actions.Initialiser
import com.nomanr.composeui.plugin.utils.Logger
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option

abstract class ComposeUITask : DefaultTask() {
    @set:Option(option = "init", description = "Initialize Compose UI Plugin")
    @get:Input
    var init: Boolean = false


    @set:Option(option = "add", description = "Add a new Compose UI Component")
    @get:Input
    @Optional
    var componentToAdd: String? = null

    private val initialiser = Initialiser(project)

    @TaskAction
    fun execute() {
        if (noInputProvided()) {
            //TODO: Add a descriptive message and perhaps return all available options
            Logger.error("No options provided!")
            return
        }

        if (init) {
            initialiser.init()
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