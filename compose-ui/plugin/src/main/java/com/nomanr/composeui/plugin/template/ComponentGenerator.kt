package com.nomanr.composeui.plugin.template

import com.nomanr.composeui.exceptions.ComposeUIException
import com.nomanr.composeui.plugin.configs.ComposeUIConfig
import com.nomanr.composeui.utils.LinkFormatter
import com.nomanr.composeui.utils.Logger
import java.io.File

class ComponentGenerator(
    private val rootDir: File,
    private val config: ComposeUIConfig
) {
    private val logger = Logger.getInstance()
    private val outputDir = File(config.componentsDir)
    private val successfullyGenerated = mutableListOf<File>()
    private val otherSuccessMessages = mutableListOf<String>()
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

        template.componentFiles.forEach { componentPath ->
            val componentOutputFile = File(outputDir, componentPath.replace(".kt.template", ".kt"))
            ensureDirectoryExists(componentOutputFile)

            if (componentOutputFile.exists()) {
                failedToGenerate.add(componentOutputFile)
            } else {
                try {
                    generateTemplate(componentPath, componentOutputFile)
                    successfullyGenerated.add(componentOutputFile)
                } catch (e: Exception) {
                    failedToGenerate.add(componentOutputFile)
                }
            }
        }

        template.supportingFiles.forEach { dependencyPath ->
            val dependencyOutputFile = File(outputDir, dependencyPath.replace(".kt.template", ".kt"))
            ensureDirectoryExists(dependencyOutputFile)

            if (dependencyOutputFile.exists()) {
                failedToGenerate.add(dependencyOutputFile)
            } else {
                try {
                    generateTemplate(dependencyPath, dependencyOutputFile)
                    successfullyGenerated.add(dependencyOutputFile)


                    if(!template.requirements.isNullOrEmpty()){
                        otherSuccessMessages.add(template.requirements)
                    }
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
            .replace("{{themeName}}", config.themeName)

        outputFile.writeText(content)
    }

    private fun ensureDirectoryExists(file: File) {
        val parentDir = file.parentFile
        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                throw ComposeUIException("Failed to create directory: ${parentDir.absolutePath}")
            }
        }
    }

    private fun logSummary(componentName: String) {
        val successLinks = successfullyGenerated.joinToString("\n") { linkFormatter.formatLink(rootDir, it) }
        val otherSuccessMessages = otherSuccessMessages.joinToString("\n")
        val failedLinks = failedToGenerate.joinToString("\n") { linkFormatter.formatLink(rootDir, it) }

        logger.success("'$componentName' generated successfully.")
        logger.info("Generated files:")
        logger.info(successLinks)

        if (otherSuccessMessages.isNotEmpty()) {
            logger.info(otherSuccessMessages)
        }

        if (failedToGenerate.isNotEmpty()) {
            logger.warn("Failed to generate some files as they already exist:")
            logger.warn(failedLinks)
        }
    }
}
