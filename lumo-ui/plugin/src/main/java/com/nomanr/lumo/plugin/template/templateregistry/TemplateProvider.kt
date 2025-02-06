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

        val allSupportingFiles = mutableListOf<String>().apply { addAll(template.supportingFiles) }
        val allPlatformSpecificSupportingFiles = mutableMapOf<MultiplatformSourceSet, MutableList<String>>()
        val visited = mutableSetOf<SupportedComponents>()
        val queue = ArrayDeque<SupportedComponents>()

        queue.addAll(template.dependsOn)

        while (queue.isNotEmpty()) {
            val currentComponent = queue.removeFirst()
            if (!visited.add(currentComponent)) continue

            val currentTemplate = getTemplate(currentComponent)

            allSupportingFiles.addAll(currentTemplate.componentFiles)
            allSupportingFiles.addAll(currentTemplate.supportingFiles)

            currentTemplate.platformSpecificFiles.forEach { (key, value) ->
                allPlatformSpecificSupportingFiles.computeIfAbsent(key) { mutableListOf() }.addAll(value)
            }
            currentTemplate.platformSpecificSupportingFiles.forEach { (key, value) ->
                allPlatformSpecificSupportingFiles.computeIfAbsent(key) { mutableListOf() }.addAll(value)
            }

            queue.addAll(currentTemplate.dependsOn)
        }

        return template.copy(
            supportingFiles = allSupportingFiles,
            platformSpecificSupportingFiles = allPlatformSpecificSupportingFiles,
        )
    }

    fun getAllComponents(): List<SupportedComponents> {
        return templates.keys.toList()
    }
}
