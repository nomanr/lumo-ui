package com.nomanr.lumo.plugin.template

import com.nomanr.lumo.plugin.template.SupportedComponents.Accordion
import com.nomanr.lumo.plugin.template.SupportedComponents.AlertDialog
import com.nomanr.lumo.plugin.template.SupportedComponents.Badge
import com.nomanr.lumo.plugin.template.SupportedComponents.Button
import com.nomanr.lumo.plugin.template.SupportedComponents.Card
import com.nomanr.lumo.plugin.template.SupportedComponents.Checkbox
import com.nomanr.lumo.plugin.template.SupportedComponents.Chip
import com.nomanr.lumo.plugin.template.SupportedComponents.Divider
import com.nomanr.lumo.plugin.template.SupportedComponents.Icon
import com.nomanr.lumo.plugin.template.SupportedComponents.IconButton
import com.nomanr.lumo.plugin.template.SupportedComponents.ModalBottomSheet
import com.nomanr.lumo.plugin.template.SupportedComponents.NavigationBar
import com.nomanr.lumo.plugin.template.SupportedComponents.OTPTextField
import com.nomanr.lumo.plugin.template.SupportedComponents.ProgressIndicator
import com.nomanr.lumo.plugin.template.SupportedComponents.RadioButton
import com.nomanr.lumo.plugin.template.SupportedComponents.Scaffold
import com.nomanr.lumo.plugin.template.SupportedComponents.Slider
import com.nomanr.lumo.plugin.template.SupportedComponents.Snackbar
import com.nomanr.lumo.plugin.template.SupportedComponents.Surface
import com.nomanr.lumo.plugin.template.SupportedComponents.Switch
import com.nomanr.lumo.plugin.template.SupportedComponents.Text
import com.nomanr.lumo.plugin.template.SupportedComponents.TextField
import com.nomanr.lumo.plugin.template.SupportedComponents.Theme
import com.nomanr.lumo.plugin.template.SupportedComponents.Tooltip
import com.nomanr.lumo.plugin.template.SupportedComponents.TopBar

object TemplateRegistry {
    private val surface = Template(
        componentFiles = listOf("components/Surface.kt.template")
    )

    private val text = Template(
        componentFiles = listOf("components/Text.kt.template"),
    )

    private val accordion = Template(
        componentFiles = listOf("components/Accordion.kt.template")
    )

    private val alertDialog = Template(
        componentFiles = listOf("components/AlertDialog.kt.template"), supportingFiles = listOf(
            "foundation/Providers.kt.template",
        )
    )

    private val badge = Template(
        componentFiles = listOf("components/Badge.kt.template"), supportingFiles = listOf(
            "foundation/Providers.kt.template",
        )
    )

    private val button = Template(
        componentFiles = listOf("components/Button.kt.template"), supportingFiles = listOf(
            *surface.allRequiredFiles(),
            *text.allRequiredFiles(),
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

    private val chip = Template(
        componentFiles = listOf("components/Chip.kt.template"), supportingFiles = listOf(
            *surface.allRequiredFiles(),
            "foundation/ButtonElevation.kt.template",
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

    private val navigationBar = Template(
        componentFiles = listOf("components/NavigationBar.kt.template"), supportingFiles = listOf(
            *surface.allRequiredFiles(),
        )
    )

    private val otpTextField = Template(
        componentFiles = listOf(
            "components/otp_textfield/OTPTextField.kt.template",
            "components/otp_textfield/OTPTextFieldDefaults.kt.template",
        )
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

    private val snackBar = Template(
        componentFiles = listOf("components/snackbar/Snackbar.kt.template", "components/snackbar/SnackbarHost.kt.template"),
        supportingFiles = listOf(
            "foundation/Providers.kt.template",
            *surface.allRequiredFiles(),
        )
    )

    private val switch = Template(
        componentFiles = listOf("components/Switch.kt.template"), supportingFiles = listOf(
            "foundation/Ripple.kt.template",
        )
    )


    private val textField = Template(
        componentFiles = listOf(
            "components/textfield/TextField.kt.template",
            "components/textfield/OutlinedTextField.kt.template",
            "components/textfield/UnderlinedTextField.kt.template",
            "components/textfield/base/TextFieldColors.kt.template",
            "components/textfield/base/TextFieldDecoration.kt.template",
            "components/textfield/base/TextFieldLayout.kt.template",
        ), supportingFiles = listOf(
            "foundation/Providers.kt.template",
        )
    )

    private val theme = Template(
        componentFiles = listOf("Theme.kt.template", "Color.kt.template", "Typography.kt.template"),
        supportingFiles = listOf("foundation/Ripple.kt.template"),
    )

    private val toolTip = Template(
        componentFiles = listOf("components/Tooltip.kt.template")
    )

    private val topBar = Template(
        componentFiles = listOf(
            "components/topbar/TopBar.kt.template", "components/topbar/TopBarScrollBehaviours.kt.template"
        ), supportingFiles = listOf(
            "foundation/SystemBarsDefaultInsets.kt.template",
            "components/Surface.kt.template",
        )
    )

    private val templates = mapOf(
        Accordion to accordion,
        AlertDialog to alertDialog,
        Badge to badge,
        Button to button,
        Card to card,
        Checkbox to checkbox,
        Chip to chip,
        Divider to divider,
        Icon to icon,
        IconButton to iconButton,
        ModalBottomSheet to modalBottomSheet,
        NavigationBar to navigationBar,
        OTPTextField to otpTextField,
        ProgressIndicator to progressIndicator,
        RadioButton to radioButton,
        Scaffold to scaffold,
        Slider to slider,
        Surface to surface,
        Snackbar to snackBar,
        Switch to switch,
        Text to text,
        TextField to textField,
        Theme to theme,
        Tooltip to toolTip,
        TopBar to topBar,
    )

    fun getTemplate(component: SupportedComponents): Template {
        return templates[component] ?: error("Template not found for $component")
    }
}