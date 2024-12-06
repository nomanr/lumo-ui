package com.nomanr.lumo.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class LumoGradlePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val rootProject = project.rootProject

        rootProject.tasks.register(TASK_NAME, LumoTask::class.java)
    }

    companion object {
        const val TASK_NAME = "lumo"
    }
}
