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
            *surface.allRequiredFiles(),
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

    private val button = Template(
        fileName = "components/Button.kt.template",
        requiredFiles = listOf(
            *surface.allRequiredFiles(),
            "foundation/ButtonElevation.kt.template",
        )
    )

    private val icon = Template(
        fileName = "components/Icon.kt.template",
    )


    private val iconButton = Template(
        fileName = "components/IconButton.kt.template",
        requiredFiles = listOf(
            *surface.allRequiredFiles(),
            "foundation/ButtonElevation.kt.template",
        )
    )

    private val modalBottomSheet = Template(
        fileName = "components/ModalBottomSheet.kt.template",
        requirements =
            "Note: Add the following dependency to the project to use this component: \n" +
                    "implementation(\"com.nomanr:composables:{version}\")\n" +
                    "Reference: https://github.com/nomanr/compose-components",
    )

    private val templates = mapOf(
        Theme to theme,
        Text to text,
        Surface to surface,
        Card to card,
        Scaffold to scaffold,
        TopBar to topBar,
        Icon to icon,
        IconButton to iconButton,
        Button to button,
        ModalBottomSheet to modalBottomSheet,
    )

    fun getTemplate(component: SupportedComponents): Template {
        return templates[component] ?: error("Template not found for $component")
    }
}
