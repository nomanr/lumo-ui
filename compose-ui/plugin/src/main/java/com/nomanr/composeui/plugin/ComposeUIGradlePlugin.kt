package com.nomanr.composeui.plugin

import com.nomanr.composeui.plugin.utils.Logger
import org.gradle.api.Plugin
import org.gradle.api.Project

class ComposeUIGradlePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val rootProject = project.rootProject

        rootProject.tasks.register(TASK_NAME) {
            group = "Compose UI Plugin task"
            description = "Add Compose UI Components directly from the command line. "

            doLast {
                val add = project.findProperty("add")?.toString()

                if (add != null) {
                    Logger.success("Adding $add!")
                } else {
                    Logger.error("No action specified. Use -Padd=<item> to specify what to add.")
                }
            }
        }
    }

    companion object {
        const val TASK_NAME = "compose"
    }
}
