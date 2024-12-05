package com.nomanr.composeui.plugin.template

import com.nomanr.composeui.plugin.template.SupportedComponents.*

object TemplateRegistry {
    private val surface = Template(
        fileName = "components/Surface.kt.template"
    )

    private val accordion = Template(
        fileName = "components/Accordion.kt.template",
    )

    private val button = Template(
        fileName = "components/Button.kt.template",
        requiredFiles = listOf(
            *surface.allRequiredFiles(),
            "foundation/ButtonElevation.kt.template",
        )
    )

    private val card = Template(
        fileName = "components/card/Card.kt.template",
        requiredFiles = listOf(
            *surface.allRequiredFiles(),
            "components/card/CardElevation.kt.template",
            "foundation/Elevation.kt.template"
        )
    )

    private val divider = Template(
        fileName = "components/Divider.kt.template",
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

    private val progressIndicator = Template(
        fileName = "components/progress_indicator/CircularProgressIndicator.kt.template",
        requiredFiles = listOf(
            "components/progress_indicator/LinearProgressIndicator.kt.template"
        )
    )

    private val radioButton = Template(
        fileName = "components/RadioButton.kt.template",
        requiredFiles = listOf(
            "foundation/Ripple.kt.template",
        )
    )

    private val scaffold = Template(
        fileName = "components/Scaffold.kt.template",
        requiredFiles = listOf(
            "foundation/SystemBarsDefaultInsets.kt.template",
        )
    )

    private val slider = Template(
        fileName = "components/Slider.kt.template",
        requirements =
        "Note: Add the following dependency to the project to use this component: \n" +
                "implementation(\"com.nomanr:composables:{version}\")\n" +
                "Reference: https://github.com/nomanr/compose-components",
    )

    private val text = Template(
        fileName = "components/Text.kt.template",
    )

    private val theme = Template(
        fileName = "Theme.kt.template",
        requiredFiles = listOf("Color.kt.template", "Typography.kt.template", "foundation/Ripple.kt.template"),
    )

    private val topBar = Template(
        fileName = "components/topbar/TopBar.kt.template",
        requiredFiles = listOf(
            "components/topbar/TopBarScrollBehaviours.kt.template",
            "foundation/SystemBarsDefaultInsets.kt.template",
        )
    )

    private val templates = mapOf(
        Accordion to accordion,
        Button to button,
        Card to card,
        Divider to divider,
        Icon to icon,
        IconButton to iconButton,
        ModalBottomSheet to modalBottomSheet,
        ProgressIndicator to progressIndicator,
        RadioButton to radioButton,
        Scaffold to scaffold,
        Slider to slider,
        Surface to surface,
        Text to text,
        Theme to theme,
        TopBar to topBar,
    )

    fun getTemplate(component: SupportedComponents): Template {
        return templates[component] ?: error("Template not found for $component")
    }
}