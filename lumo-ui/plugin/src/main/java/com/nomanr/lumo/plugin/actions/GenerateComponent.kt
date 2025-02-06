package com.nomanr.lumo.plugin.actions

import com.nomanr.lumo.exceptions.LumoException
import com.nomanr.lumo.plugin.configs.PropertyLoader
import com.nomanr.lumo.plugin.template.ComponentGenerator
import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents
import com.nomanr.lumo.utils.Logger
import org.gradle.api.Project

class GenerateComponent(project: Project, propertyLoader: PropertyLoader) {
    private val componentGenerator = ComponentGenerator(project.rootDir, propertyLoader.loadProperties())
    private val logger = Logger.getInstance()

    fun execute(componentName: String) {
        if (componentName.isEmpty() || !SupportedComponents.values().map { it.name }.contains(componentName)) {
            throw LumoException("Invalid component name ${componentName}. Find supported components: https://lumoui.com")
        }

        execute(SupportedComponents.valueOf(componentName))
    }

    fun execute(component: SupportedComponents) {
        componentGenerator.validateAndGenerate(component)
    }

    fun executeAll() {
        componentGenerator.generateAll()
    }

    fun printAllAvailableComponents() {
        val components = componentGenerator.templateProvider.getAllComponents()
        logger.log("Available components:")
        components.forEach { logger.log(it.name) }
    }
}
