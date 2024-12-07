package com.nomanr.lumo.plugin.template

import com.nomanr.lumo.plugin.template.SupportedComponents.*

object TemplateRegistry {
    private val surface = Template(
        componentFiles = listOf("components/Surface.kt.template")
    )

    private val accordion = Template(
        componentFiles = listOf("components/Accordion.kt.template")
    )

    private val button = Template(
        componentFiles = listOf("components/Button.kt.template"), supportingFiles = listOf(
            *surface.allRequiredFiles(),
            "foundation/ButtonElevation.kt.template",
        )
    )

    private val card = Template(
        componentFiles = listOf("components/card/Card.kt.template"), supportingFiles = listOf(
            *surface.allRequiredFiles(), "components/card/CardElevation.kt.template", "foundation/Elevation.kt.template"
        )
    )
    private val checkbox = Template(
        componentFiles = listOf("components/Checkbox.kt.template"), supportingFiles = listOf(
            "foundation/Ripple.kt.template",
        )
    )

    private val divider = Template(
        componentFiles = listOf("components/Divider.kt.template"),
    )

    private val icon = Template(
        componentFiles = listOf("components/Icon.kt.template"),
    )

    private val iconButton = Template(
        componentFiles = listOf("components/IconButton.kt.template"), supportingFiles = listOf(
            *surface.allRequiredFiles(),
            "foundation/ButtonElevation.kt.template",
        )
    )

    private val modalBottomSheet = Template(
        componentFiles = listOf("components/ModalBottomSheet.kt.template"),
        requirements = "Note: Add the following dependency to the project to use this component: \n" + "implementation(\"com.nomanr:composables:{version}\")\n" + "Reference: https://github.com/nomanr/compose-components",
    )

    private val progressIndicator = Template(
        componentFiles = listOf(
            "components/progress_indicator/CircularProgressIndicator.kt.template",
            "components/progress_indicator/LinearProgressIndicator.kt.template"
        )
    )

    private val radioButton = Template(
        componentFiles = listOf("components/RadioButton.kt.template"), supportingFiles = listOf(
            "foundation/Ripple.kt.template",
        )
    )

    private val scaffold = Template(
        componentFiles = listOf("components/Scaffold.kt.template"), supportingFiles = listOf(
            "foundation/SystemBarsDefaultInsets.kt.template",
        )
    )

    private val slider = Template(
        componentFiles = listOf("components/Slider.kt.template"),
        requirements = "Note: Add the following dependency to the project to use this component: \n" + "implementation(\"com.nomanr:composables:{version}\")\n" + "Reference: https://github.com/nomanr/compose-components",
    )

    private val text = Template(
        componentFiles = listOf("components/Text.kt.template"),
    )

    private val textField = Template(
        componentFiles = listOf(
            "components/textfield/TextField.kt.template",
            "components/textfield/OutlinedTextField.kt.template",
            "components/textfield/UnderlinedTextField.kt.template",
            "components/textfield/base/TextFieldColors.kt.template",
            "components/textfield/base/TextFieldDecoration.kt.template",
            "components/textfield/base/TextFieldLayout.kt.template",
        ),
        supportingFiles = listOf(
            "foundation/Providers.kt.template",
        )
    )

    private val theme = Template(
        componentFiles = listOf("Theme.kt.template", "Color.kt.template", "Typography.kt.template"),
        supportingFiles = listOf("foundation/Ripple.kt.template"),
    )

    private val topBar = Template(
        componentFiles = listOf(
            "components/topbar/TopBar.kt.template", "components/topbar/TopBarScrollBehaviours.kt.template"
        ), supportingFiles = listOf(
            "foundation/SystemBarsDefaultInsets.kt.template",
        )
    )

    private val templates = mapOf(
        Accordion to accordion,
        Button to button,
        Card to card,
        Checkbox to checkbox,
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
        TextField to textField,
        Theme to theme,
        TopBar to topBar,
    )

    fun getTemplate(component: SupportedComponents): Template {
        return templates[component] ?: error("Template not found for $component")
    }
}