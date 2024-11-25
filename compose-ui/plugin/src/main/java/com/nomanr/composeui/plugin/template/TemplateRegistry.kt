package com.nomanr.composeui.plugin.template

import com.nomanr.composeui.plugin.template.SupportedComponents.*

object TemplateRegistry {
    private val theme = Template(
        fileName = "Theme.kt.template",
        requiredFiles = listOf("Color.kt.template", "Typography.kt.template", "foundation/Ripple.kt.template"),
    )

    private val text = Template(
        fileName = "components/Text.kt.template",
    )

    private val surface = Template(
        fileName = "components/Surface.kt.template"
    )

    private val templates = mapOf(
        Theme to theme,
        Text to text,
        Surface to surface,
    )

    fun getTemplate(component: SupportedComponents): Template {
        return templates[component] ?: error("Template not found for $component")
    }
}
