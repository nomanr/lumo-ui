package com.nomanr.lumo.plugin.template.templateregistry

import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.Accordion
import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.AlertDialog
import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.Badge
import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.Button
import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.Card
import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.Checkbox
import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.Chip
import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.Divider
import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.Icon
import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.IconButton
import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.NavigationBar
import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.OTPTextField
import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.ProgressIndicators
import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.RadioButton
import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.Scaffold
import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.Snackbar
import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.Surface
import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.Switch
import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.Text
import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.TextField
import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.Theme
import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.TopBar

object CommonTemplates {

    private val alertDialog =
        Template(
            componentFiles = listOf("components/AlertDialog.kt.template"),
            supportingFiles =
            listOf(
                "foundation/Providers.kt.template",
            ),
            dependsOn = listOf(Button),
        )

    private val surface =
        Template(
            componentFiles = listOf("components/Surface.kt.template"),
        )

    private val text =
        Template(
            componentFiles = listOf("components/Text.kt.template"),
        )

    private val accordion =
        Template(
            componentFiles = listOf("components/Accordion.kt.template"),
        )

    private val button =
        Template(
            componentFiles = listOf("components/Button.kt.template"),
            supportingFiles = listOf("foundation/ButtonElevation.kt.template"),
            dependsOn = listOf(Surface, Text),
        )

    private val badge =
        Template(
            componentFiles = listOf("components/Badge.kt.template"),
            supportingFiles = listOf("foundation/Providers.kt.template"),
        )

    private val card =
        Template(
            componentFiles = listOf("components/card/Card.kt.template"),
            supportingFiles =
            listOf(
                "components/card/CardElevation.kt.template",
                "foundation/Elevation.kt.template",
            ),
            dependsOn = listOf(Surface),
        )

    private val checkbox =
        Template(
            componentFiles = listOf("components/Checkbox.kt.template"),
            supportingFiles = listOf("foundation/Ripple.kt.template"),
        )

    private val chip =
        Template(
            componentFiles = listOf("components/Chip.kt.template"),
            supportingFiles = listOf("foundation/ButtonElevation.kt.template"),
            dependsOn = listOf(Surface),
        )

    private val divider =
        Template(
            componentFiles = listOf("components/Divider.kt.template"),
        )

    private val icon =
        Template(
            componentFiles = listOf("components/Icon.kt.template"),
        )

    private val iconButton =
        Template(
            componentFiles = listOf("components/IconButton.kt.template"),
            supportingFiles = listOf("foundation/ButtonElevation.kt.template"),
            dependsOn = listOf(Surface),
        )

    private val navigationBar =
        Template(
            componentFiles = listOf("components/NavigationBar.kt.template"),
            supportingFiles = listOf("foundation/SystemBarsDefaultInsets.kt.template", "foundation/Providers.kt.template"),
            dependsOn = listOf(Surface),
        )

    private val otpTextField =
        Template(
            componentFiles =
            listOf(
                "components/otptextfield/OTPTextField.kt.template",
                "components/otptextfield/OTPTextFieldDefaults.kt.template",
            ),
        )

    private val progressIndicator =
        Template(
            componentFiles =
            listOf(
                "components/progressindicators/CircularProgressIndicator.kt.template",
                "components/progressindicators/LinearProgressIndicator.kt.template",
            ),
        )

    private val radioButton =
        Template(
            componentFiles = listOf("components/RadioButton.kt.template"),
            supportingFiles = listOf("foundation/Ripple.kt.template"),
        )

    private val scaffold =
        Template(
            componentFiles = listOf("components/Scaffold.kt.template"),
            supportingFiles = listOf("foundation/SystemBarsDefaultInsets.kt.template"),
            dependsOn = listOf(Surface),
        )

    private val snackBar =
        Template(
            componentFiles =
            listOf(
                "components/snackbar/Snackbar.kt.template",
                "components/snackbar/SnackbarHost.kt.template",
            ),
            supportingFiles = listOf("foundation/Providers.kt.template"),
            dependsOn = listOf(Surface, Text),
        )

    private val switch =
        Template(
            componentFiles = listOf("components/Switch.kt.template"),
            supportingFiles = listOf("foundation/Ripple.kt.template"),
        )

    private val textField =
        Template(
            componentFiles =
            listOf(
                "components/textfield/TextField.kt.template",
                "components/textfield/OutlinedTextField.kt.template",
                "components/textfield/UnderlinedTextField.kt.template",
                "components/textfield/base/TextFieldColors.kt.template",
                "components/textfield/base/TextFieldDecoration.kt.template",
                "components/textfield/base/TextFieldLayout.kt.template",
            ),
            supportingFiles = listOf("foundation/Providers.kt.template"),
        )

    private val theme =
        Template(
            componentFiles = listOf("Theme.kt.template", "Color.kt.template", "Typography.kt.template"),
            supportingFiles = listOf("foundation/Ripple.kt.template", "foundation/Elevation.kt.template", "Providers.kt.template"),
        )

    private val topBar =
        Template(
            componentFiles =
            listOf(
                "components/topbar/TopBar.kt.template",
                "components/topbar/TopBarScrollBehaviours.kt.template",
            ),
            supportingFiles = listOf("foundation/SystemBarsDefaultInsets.kt.template"),
            dependsOn = listOf(Surface),
        )
    

    fun getTemplates() =
        mapOf(
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
            NavigationBar to navigationBar,
            OTPTextField to otpTextField,
            ProgressIndicators to progressIndicator,
            RadioButton to radioButton,
            Scaffold to scaffold,
            Surface to surface,
            Snackbar to snackBar,
            Switch to switch,
            Text to text,
            TextField to textField,
            Theme to theme,
            TopBar to topBar,
        )
}
