package com.nomanr.composeui.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class ComposeUIGradlePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val rootProject = project.rootProject

        rootProject.tasks.register(TASK_NAME, ComposeUITask::class.java)
    }

    companion object {
        const val TASK_NAME = "compose"
    }
}
