package com.nomanr.lumo.plugin.template

import com.nomanr.lumo.exceptions.LumoException
import com.nomanr.lumo.plugin.configs.LumoConfig
import com.nomanr.lumo.plugin.template.template_registry.MultiplatformSourceSet
import com.nomanr.lumo.plugin.template.template_registry.SupportedComponents
import com.nomanr.lumo.plugin.template.template_registry.Template
import com.nomanr.lumo.plugin.template.template_registry.TemplateProvider
import com.nomanr.lumo.utils.LinkFormatter
import com.nomanr.lumo.utils.Logger
import java.io.File

class ComponentGenerator(
    private val rootDir: File, private val config: LumoConfig
) {
    private val logger = Logger.getInstance()
    private val outputDir = File(config.componentsDir)
    private val successfullyGenerated = mutableListOf<File>()
    private val successFullyGeneratedSupportingFiles = mutableListOf<File>()
    private val otherSuccessMessages = mutableListOf<String>()
    private val failedToGenerate = mutableListOf<File>()
    private val linkFormatter = LinkFormatter
    private val templateProvider = TemplateProvider(config.kotlinMultiplatform)

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

        val templateSourceDir = templateProvider.templateSourceDir
        val template = templateProvider.getTemplate(component)

        template.componentFiles.forEach { componentPath ->
            val componentOutputFile = File(outputDir, componentPath.replace(".kt.template", ".kt"))
            ensureDirectoryExists(componentOutputFile)

            if (componentOutputFile.exists()) {
                failedToGenerate.add(componentOutputFile)
            } else {
                try {
                    generateTemplate(componentPath, componentOutputFile, templateSourceDir)
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
                    generateTemplate(dependencyPath, dependencyOutputFile, templateSourceDir)
                    successFullyGeneratedSupportingFiles.add(dependencyOutputFile)


                    if (!template.requirements.isNullOrEmpty()) {
                        otherSuccessMessages.add(template.requirements)
                    }
                } catch (e: Exception) {
                    failedToGenerate.add(dependencyOutputFile)
                }
            }
        }

        if (config.kotlinMultiplatform) {
            generatePlatformSpecificFiles(template, templateSourceDir)
        }

        logSummary(component.name)
    }

    private fun generatePlatformSpecificFiles(
        template: Template, templateSourceDir: String
    ) {
        template.platformSpecificFiles.forEach { (sourceSet, files) ->
            val platformOutputDir =
                config.componentsDir.replace(MultiplatformSourceSet.COMMON.sourceSetName, sourceSet.sourceSetName)
            logger.error(platformOutputDir)
            files.forEach { file ->
                val outputFile = File(platformOutputDir, file.replace(".kt.template", ".kt"))
                ensureDirectoryExists(outputFile)

                if (outputFile.exists()) {
                    failedToGenerate.add(outputFile)
                } else {
                    try {
                        generateTemplate(file, outputFile, templateSourceDir)
                        successfullyGenerated.add(outputFile)
                    } catch (e: Exception) {
                        failedToGenerate.add(outputFile)
                    }
                }
            }
        }

        template.platformSpecificSupportingFiles.forEach { (sourceSet, files) ->
            val platformOutputDir = config.componentsDir.replace(MultiplatformSourceSet.COMMON.name, sourceSet.name)
            files.forEach { file ->
                val outputFile = File(platformOutputDir, file.replace(".kt.template", ".kt"))
                ensureDirectoryExists(outputFile)

                if (outputFile.exists()) {
                    failedToGenerate.add(outputFile)
                } else {
                    try {
                        generateTemplate(file, outputFile, templateSourceDir)
                        successFullyGeneratedSupportingFiles.add(outputFile)
                    } catch (e: Exception) {
                        failedToGenerate.add(outputFile)
                    }
                }
            }
        }
    }

    private fun generateTemplate(
        templateFileName: String,
        outputFile: File,
        templateSourceDir: String,
    ) {
        val resourcePath = "$templateSourceDir/$templateFileName"
        val resource = javaClass.classLoader.getResource(resourcePath)
            ?: throw IllegalArgumentException("Template file $templateFileName not found in resources.")

        val templateContent = resource.readText()

        val content = templateContent.replace("{{packageName}}", config.packageName).replace("{{themeName}}", config.themeName)

        outputFile.writeText(content)
    }

    private fun ensureDirectoryExists(file: File) {
        val parentDir = file.parentFile
        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                throw LumoException("Failed to create directory: ${parentDir.absolutePath}")
            }
        }
    }

    private fun logSummary(componentName: String) {
        val successLinks = successfullyGenerated.joinToString("\n") { linkFormatter.formatLink(rootDir, it) }
        val successSupportingLinks =
            successFullyGeneratedSupportingFiles.joinToString("\n") { linkFormatter.formatLink(rootDir, it) }
        val otherSuccessMessages = otherSuccessMessages.joinToString("\n")
        val failedLinks = failedToGenerate.joinToString("\n") { linkFormatter.formatLink(rootDir, it) }




        if (failedToGenerate.isNotEmpty()) {
            logger.warn("Failed to generate some files as they already exist:")
            logger.warn(failedLinks)
        }

        if (successSupportingLinks.isNotEmpty()) {
            logger.info("\nGenerated supporting files:")
            logger.info(successSupportingLinks)
        }

        if (successLinks.isNotEmpty()) {
            logger.info("\nGenerated '$componentName' files:")
            logger.info(successLinks)
            println()
        }

        val totalGenerated = successfullyGenerated.size + successFullyGeneratedSupportingFiles.size
        val totalFailed = failedToGenerate.size

        if (otherSuccessMessages.isNotEmpty()) {
            logger.info(otherSuccessMessages)
        }

        if (successfullyGenerated.isNotEmpty()) {
            logger.success("'$componentName' generated successfully.")
        }
        logger.success("Generated Files: $totalGenerated")
        if (totalFailed > 0) {
            logger.warn("Failed to Generate: $totalFailed")
        }

        if(totalGenerated > 0) {
            logger.info("Scroll up to see the generated files.")
        }
    }
}
