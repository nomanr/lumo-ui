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

    private val card = Template(
        fileName = "components/card/Card.kt.template",
        requiredFiles = listOf(
            surface.fileName,
            *surface.requiredFilesAsArray(),
            "components/card/CardElevation.kt.template",
            "foundation/Elevation.kt.template"
        )
    )

    private val scaffold = Template(
        fileName = "components/Scaffold.kt.template",
        requiredFiles = listOf(
            "foundation/SystemBarsDefaultInsets.kt.template",
        )
    )

    private val topBar = Template(
        fileName = "components/topbar/TopBar.kt.template",
        requiredFiles = listOf(
            "components/topbar/TopBarScrollBehaviours.kt.template",
            "foundation/SystemBarsDefaultInsets.kt.template",
        )
    )

    private val templates = mapOf(
        Theme to theme,
        Text to text,
        Surface to surface,
        Card to card,
        Scaffold to scaffold,
        TopBar to topBar,
    )

    fun getTemplate(component: SupportedComponents): Template {
        return templates[component] ?: error("Template not found for $component")
    }
}
