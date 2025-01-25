package com.nomanr.lumo.plugin.template

import com.nomanr.lumo.exceptions.LumoException
import com.nomanr.lumo.plugin.configs.LumoConfig
import com.nomanr.lumo.utils.LinkFormatter
import com.nomanr.lumo.utils.Logger
import java.io.File

class ComponentGenerator(
    private val rootDir: File,
    private val config: LumoConfig
) {
    private val logger = Logger.getInstance()
    private val outputDir = File(config.componentsDir + File.separator + "commonMain/kotlin/" + packageNameToPath(config.packageName))
    private val androidOutputDir = File(config.componentsDir + File.separator + "androidMain/kotlin/" + packageNameToPath(config.packageName))
    private val iOSOutputDir = File(config.componentsDir + File.separator + "iosMain/kotlin/" + packageNameToPath(config.packageName))
    private val macosOutputDir = File(config.componentsDir + File.separator + "macosMain/kotlin/" + packageNameToPath(config.packageName))
    private val desktopOutputDir = File(config.componentsDir + File.separator + "desktopMain/kotlin/" + packageNameToPath(config.packageName))
    private val wasmJsOutputDir = File(config.componentsDir + File.separator + "wasmJsMain/kotlin/" + packageNameToPath(config.packageName))
    private val successfullyGenerated = mutableListOf<File>()
    private val successFullyGeneratedSupportingFiles = mutableListOf<File>()
    private val otherSuccessMessages = mutableListOf<String>()
    private val failedToGenerate = mutableListOf<File>()
    private val linkFormatter = LinkFormatter

    init {
        for (dir in listOf(outputDir, androidOutputDir, iOSOutputDir, macosOutputDir, desktopOutputDir, wasmJsOutputDir)) {
            if (!dir.exists()) {
                if (dir.mkdirs()) {
                    logger.info("Created base output directory: ${dir.absolutePath}")
                } else {
                    throw IllegalStateException("Failed to create base output directory: ${dir.absolutePath}")
                }
            }
        }
    }

    fun validateAndGenerate(component: SupportedComponents) {
        logger.info("Generating ${component.name} ...")
        val template = TemplateRegistry.getTemplate(component)

        template.componentFiles.forEach { componentPath ->
            val outputPath = getOutputDirectory(outputDir, componentPath)
            val componentOutputFile = File(outputPath, componentPath.replace(".kt.template", ".kt"))
            ensureDirectoryExists(componentOutputFile)

            if (componentOutputFile.exists()) {
                failedToGenerate.add(componentOutputFile)
            } else {
                try {
                    generateComponentFromTemplate(componentPath, componentOutputFile, "templates/commonMain")
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
                    generateComponentFromTemplate(dependencyPath, dependencyOutputFile, "templates/commonMain")
                    successFullyGeneratedSupportingFiles.add(dependencyOutputFile)

                    if(!template.requirements.isNullOrEmpty()){
                        otherSuccessMessages.add(template.requirements)
                    }
                } catch (e: Exception) {
                    failedToGenerate.add(dependencyOutputFile)
                }
            }
        }

        template.multiplatformFiles.forEach { (platform, platformSpecificImplementationFiles) ->
            val multiplatformTemplateParentPath = when (platform) {
                SupportedPlatforms.ANDROID -> "templates/androidMain"
                SupportedPlatforms.IOS -> "templates/iosMain"
                SupportedPlatforms.MACOS -> "templates/macosMain"
                SupportedPlatforms.DESKTOP -> "templates/desktopMain"
                SupportedPlatforms.WASMJS -> "templates/wasmJsMain"
            }

            platformSpecificImplementationFiles.forEach { multiplatformTemplateFile ->
                val outputPath = getOutputDirectory(outputDir, multiplatformTemplateFile)
                val dependencyOutputFile = File(outputPath, multiplatformTemplateFile.replace(".kt.template", ".kt"))
                ensureDirectoryExists(dependencyOutputFile)

                if (dependencyOutputFile.exists()) {
                    failedToGenerate.add(dependencyOutputFile)
                } else {
                    try {
                        generateComponentFromTemplate(multiplatformTemplateFile, dependencyOutputFile, multiplatformTemplateParentPath)
                        successFullyGeneratedSupportingFiles.add(dependencyOutputFile)

                        if(!template.requirements.isNullOrEmpty()){
                            otherSuccessMessages.add(template.requirements)
                        }
                    } catch (e: Exception) {
                        failedToGenerate.add(dependencyOutputFile)
                    }
                }
            }
        }

        logSummary(component.name)
    }

    private fun getOutputDirectory(outputDir: File, componentTemplatePath: String): File {
        return if (componentTemplatePath.contains("ios.kt.template")) {
            iOSOutputDir
        } else if (componentTemplatePath.contains("android.kt.template")) {
            androidOutputDir
        } else if (componentTemplatePath.contains("macos.kt.template")) {
            macosOutputDir
        } else if (componentTemplatePath.contains("desktop.kt.template")) {
            desktopOutputDir
        } else if (componentTemplatePath.contains("wasmJs.kt.template")) {
            wasmJsOutputDir
        } else {
            outputDir
        }
    }

    private fun packageNameToPath(packageName: String): String {
        return packageName.replace(".", File.separator)
    }

    private fun generateComponentFromTemplate(
        templateFileName: String,
        outputFile: File,
        templateParentPath: String
    ) {
        val resourcePath = templateParentPath + File.separator + templateFileName
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
                throw LumoException("Failed to create directory: ${parentDir.absolutePath}")
            }
        }
    }

    private fun logSummary(componentName: String) {
        val successLinks = successfullyGenerated.joinToString("\n") { linkFormatter.formatLink(rootDir, it) }
        val successSupportingLinks = successFullyGeneratedSupportingFiles.joinToString("\n") { linkFormatter.formatLink(rootDir, it) }
        val otherSuccessMessages = otherSuccessMessages.joinToString("\n")
        val failedLinks = failedToGenerate.joinToString("\n") { linkFormatter.formatLink(rootDir, it) }

        if(successfullyGenerated.isNotEmpty()) {
            logger.success("'$componentName' generated successfully.")
        }

        if (otherSuccessMessages.isNotEmpty()) {
            logger.info(otherSuccessMessages)
        }

        if (failedToGenerate.isNotEmpty()) {
            logger.warn("Failed to generate some files as they already exist:")
            logger.warn(failedLinks)
        }

        if(successLinks.isNotEmpty()) {
            logger.info("Generated '$componentName' files:")
            logger.info(successLinks)
            println()
        }
        if(successSupportingLinks.isNotEmpty()) {
            logger.info("Generated supporting files:")
            logger.info(successSupportingLinks)
        }

    }
}
