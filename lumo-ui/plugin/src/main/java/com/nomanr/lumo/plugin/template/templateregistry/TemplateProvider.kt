package com.nomanr.lumo.plugin.template.templateregistry

class TemplateProvider(kotlinMultiplatform: Boolean) {
    private val templates =
        CommonTemplates.getTemplates() + if (kotlinMultiplatform) MultiplatformTemplates.getTemplate() else AndroidTemplates.getTemplates()

    val templateSourceDir = if (kotlinMultiplatform) "templates/multiplatform" else "templates/android"

    private fun getTemplate(component: SupportedComponents): Template {
        return templates[component] ?: error("Template not found for $component")
    }

    fun getFlattenedTemplate(component: SupportedComponents): Template {
        val template = getTemplate(component)

        val allSupportingFiles = template.supportingFiles.toMutableList()
        val allPlatformSpecificSupportingFiles = template.platformSpecificSupportingFiles.toMutableMap()

        var dependsOnTemplate: Template? = template.dependsOn.firstOrNull()?.let { getTemplate(it) }

        while (dependsOnTemplate != null) {
            allSupportingFiles.addAll(
                dependsOnTemplate.componentFiles +
                    dependsOnTemplate.supportingFiles,
            )
            allPlatformSpecificSupportingFiles.putAll(
                dependsOnTemplate.platformSpecificFiles +
                    dependsOnTemplate.platformSpecificSupportingFiles,
            )

            dependsOnTemplate = dependsOnTemplate.dependsOn.firstOrNull()?.let { getTemplate(it) }
        }

        return template.copy(
            supportingFiles = allSupportingFiles,
            platformSpecificSupportingFiles = allPlatformSpecificSupportingFiles,
        )
    }
}
