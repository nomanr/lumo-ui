package com.nomanr.composeui.plugin.template

import com.nomanr.composeui.exceptions.ComposeUIException
import com.nomanr.composeui.plugin.configs.ComposeUIConfig
import com.nomanr.composeui.utils.Logger
import com.nomanr.composeui.utils.LinkFormatter
import java.io.File

class ComponentGenerator(private val config: ComposeUIConfig) {
    private val logger = Logger.getInstance()
    private val outputDir = File(config.componentsDir)
    private val successfullyGenerated = mutableListOf<File>()
    private val failedToGenerate = mutableListOf<File>()
    private val linkFormatter = LinkFormatter

    init {
        if (!outputDir.exists()) {
            if (outputDir.mkdirs()) {
                logger.info("Created base output directory: ${outputDir.absolutePath}")
            } else {
                throw IllegalStateException("Failed to create base output directory: ${outputDir.absolutePath}")
            }
        }
    }

    fun validateAndGenerate(component: SupportedComponents) {
        logger.info("Generating ${component.name} ...")
        val template = TemplateRegistry.getTemplate(component)

        val componentFile = File(outputDir, template.fileName.replace(".kt.template", ".kt"))
        if (componentFile.exists()) {
            throw ComposeUIException("Component '${component.name}' already exists. Nothing to generate.")
        }

        ensureDirectoryExists(componentFile)
        generateTemplate(template.fileName, componentFile)
        successfullyGenerated.add(componentFile)

        template.requiredFiles.forEach { dependencyPath ->
            val dependencyOutputFile = File(outputDir, dependencyPath.replace(".kt.template", ".kt"))
            ensureDirectoryExists(dependencyOutputFile)

            if (dependencyOutputFile.exists()) {
                failedToGenerate.add(dependencyOutputFile)
            } else {
                try {
                    generateTemplate(dependencyPath, dependencyOutputFile)
                    successfullyGenerated.add(dependencyOutputFile)
                } catch (e: Exception) {
                    failedToGenerate.add(dependencyOutputFile)
                }
            }
        }

        logSummary(component.name)
    }
    private fun generateTemplate(templateFileName: String, outputFile: File) {
        val resourcePath = "templates/$templateFileName"
        val resource = javaClass.classLoader.getResource(resourcePath)
            ?: throw IllegalArgumentException("Template file $templateFileName not found in resources.")

        val templateContent = resource.readText()

        val content = templateContent.replace("{{packageName}}", config.packageName)

        outputFile.writeText(content)
    }

    private fun ensureDirectoryExists(file: File) {
        val parentDir = file.parentFile
        if (parentDir != null && !parentDir.exists()) {
            if (parentDir.mkdirs()) {
                logger.info("Created directory: ${parentDir.absolutePath}")
            } else {
                throw IllegalStateException("Failed to create directory: ${parentDir.absolutePath}")
            }
        }
    }

    private fun logSummary(componentName: String) {
        val successLinks = successfullyGenerated.joinToString("\n") { linkFormatter.formatLink(it) }
        val failedLinks = failedToGenerate.joinToString("\n") { linkFormatter.formatLink(it) }

        logger.info("$componentName generated successfully.")
        logger.info("Generated files:")
        logger.info(successLinks)

        if (failedToGenerate.isNotEmpty()) {
            logger.warn("Failed to generate some files as they already exist:")
            logger.warn(failedLinks)
        }
    }
}