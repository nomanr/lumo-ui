package com.nomanr.lumo.multiplatform.sample.sample

import androidx.compose.runtime.Composable
import com.nomanr.lumo.multiplatform.sample.sample.ComponentId.ACCORDION
import com.nomanr.lumo.multiplatform.sample.sample.ComponentId.ALERT_DIALOG
import com.nomanr.lumo.multiplatform.sample.sample.ComponentId.BADGE
import com.nomanr.lumo.multiplatform.sample.sample.ComponentId.BUTTON
import com.nomanr.lumo.multiplatform.sample.sample.ComponentId.CARD
import com.nomanr.lumo.multiplatform.sample.sample.ComponentId.CHECKBOX
import com.nomanr.lumo.multiplatform.sample.sample.ComponentId.CHIP
import com.nomanr.lumo.multiplatform.sample.sample.ComponentId.DIVIDER
import com.nomanr.lumo.multiplatform.sample.sample.ComponentId.ICON
import com.nomanr.lumo.multiplatform.sample.sample.ComponentId.ICON_BUTTON
import com.nomanr.lumo.multiplatform.sample.sample.ComponentId.MODAL_BOTTOM_SHEET
import com.nomanr.lumo.multiplatform.sample.sample.ComponentId.NAVIGATION_BAR
import com.nomanr.lumo.multiplatform.sample.sample.ComponentId.OTP_TEXT_FIELD
import com.nomanr.lumo.multiplatform.sample.sample.ComponentId.PROGRESS_INDICATOR
import com.nomanr.lumo.multiplatform.sample.sample.ComponentId.RADIO_BUTTON
import com.nomanr.lumo.multiplatform.sample.sample.ComponentId.SCAFFOLD
import com.nomanr.lumo.multiplatform.sample.sample.ComponentId.SLIDER
import com.nomanr.lumo.multiplatform.sample.sample.ComponentId.SNACKBAR
import com.nomanr.lumo.multiplatform.sample.sample.ComponentId.SURFACE
import com.nomanr.lumo.multiplatform.sample.sample.ComponentId.SWITCH
import com.nomanr.lumo.multiplatform.sample.sample.ComponentId.TEXT
import com.nomanr.lumo.multiplatform.sample.sample.ComponentId.TEXT_FIELD
import com.nomanr.lumo.multiplatform.sample.sample.ComponentId.TOOLTIP
import com.nomanr.lumo.multiplatform.sample.sample.ComponentId.TOP_BAR
import com.nomanr.lumo.multiplatform.sample.sample.samples.AccordionSample
import com.nomanr.lumo.multiplatform.sample.sample.samples.AlertDialogSample
import com.nomanr.lumo.multiplatform.sample.sample.samples.BadgeSample
import com.nomanr.lumo.multiplatform.sample.sample.samples.ButtonSample
import com.nomanr.lumo.multiplatform.sample.sample.samples.CardSample
import com.nomanr.lumo.multiplatform.sample.sample.samples.CheckboxSample
import com.nomanr.lumo.multiplatform.sample.sample.samples.ChipSample
import com.nomanr.lumo.multiplatform.sample.sample.samples.DividerSample
import com.nomanr.lumo.multiplatform.sample.sample.samples.IconButtonSample
import com.nomanr.lumo.multiplatform.sample.sample.samples.IconSample
import com.nomanr.lumo.multiplatform.sample.sample.samples.NavigationBarSample
import com.nomanr.lumo.multiplatform.sample.sample.samples.OTPTextFieldSample
import com.nomanr.lumo.multiplatform.sample.sample.samples.ProgressIndicatorSample
import com.nomanr.lumo.multiplatform.sample.sample.samples.RadioButtonSample
import com.nomanr.lumo.multiplatform.sample.sample.samples.ScaffoldSample
import com.nomanr.lumo.multiplatform.sample.sample.samples.SnackbarSample
import com.nomanr.lumo.multiplatform.sample.sample.samples.SurfaceSample
import com.nomanr.lumo.multiplatform.sample.sample.samples.SwitchSample
import com.nomanr.lumo.multiplatform.sample.sample.samples.TextFieldSample
import com.nomanr.lumo.multiplatform.sample.sample.samples.TextSample
import com.nomanr.lumo.multiplatform.sample.sample.samples.TooltipSample
import com.nomanr.lumo.multiplatform.sample.sample.samples.TopBarSample
import kotlinx.serialization.Serializable

@Serializable
enum class ComponentId(val label: String) {
    ACCORDION("Accordion"),
    ALERT_DIALOG("Alert Dialog"),
    BADGE("Badge"),
    BUTTON("Button"),
    CARD("Card"),
    CHECKBOX("Checkbox"),
    CHIP("Chip"),
    DIVIDER("Divider"),
    ICON("Icon"),
    ICON_BUTTON("Icon Button"),
    MODAL_BOTTOM_SHEET("Modal Bottom Sheet"),
    NAVIGATION_BAR("Navigation Bar"),
    OTP_TEXT_FIELD("OTP Text Field"),
    PROGRESS_INDICATOR("Progress Indicator"),
    RADIO_BUTTON("Radio Button"),
    SCAFFOLD("Scaffold"),
    SLIDER("Slider"),
    SNACKBAR("Snackbar"),
    SURFACE("Surface"),
    SWITCH("Switch"),
    TEXT("Text"),
    TEXT_FIELD("TextField"),
    TOOLTIP("Tooltip"),
    TOP_BAR("Top App Bar"),
}

@Serializable
class Component internal constructor(
    val id: ComponentId,
    val showTopBar: Boolean = true,
) {
    val label: String
        get() = id.label

    companion object {
        private val components: List<Component> =
            listOf(
                Component(id = ACCORDION),
                Component(id = ALERT_DIALOG),
                Component(id = BADGE, showTopBar = false),
                Component(id = BUTTON),
                Component(id = CARD),
                Component(id = CHECKBOX),
                Component(id = CHIP),
                Component(id = DIVIDER),
                Component(id = ICON),
                Component(id = ICON_BUTTON),
                Component(id = MODAL_BOTTOM_SHEET),
                Component(id = NAVIGATION_BAR, showTopBar = false),
                Component(id = OTP_TEXT_FIELD),
                Component(id = PROGRESS_INDICATOR),
                Component(id = RADIO_BUTTON),
                Component(id = SCAFFOLD, false),
                Component(id = SLIDER),
                Component(id = SNACKBAR, false),
                Component(id = SURFACE),
                Component(id = SWITCH),
                Component(id = TEXT),
                Component(id = TEXT_FIELD),
                Component(id = TOOLTIP),
                Component(id = TOP_BAR, showTopBar = false),
            )

        fun getAll(): List<Component> = components

        fun getById(id: ComponentId): Component = components.first { it.id == id }
    }
}

object Samples {
    val components: Map<ComponentId, @Composable ((() -> Unit)?) -> Unit>
        get() =
            mapOf<ComponentId, @Composable ((() -> Unit)?) -> Unit>(
                TEXT to { TextSample() },
                BUTTON to { ButtonSample() },
                CHECKBOX to { CheckboxSample() },
                CHIP to { ChipSample() },
                DIVIDER to { DividerSample() },
                ICON to { IconSample() },
                ICON_BUTTON to { IconButtonSample() },
                CARD to { CardSample() },
                TOP_BAR to { navigateUp -> TopBarSample(navigateUp) },
                ACCORDION to { AccordionSample() },
                TEXT_FIELD to { TextFieldSample() },
                ALERT_DIALOG to { AlertDialogSample() },
                BADGE to { navigateUp -> BadgeSample(navigateUp) },
//                MODAL_BOTTOM_SHEET to { ModalBottomSheetSample() },
                NAVIGATION_BAR to { navigateUp -> NavigationBarSample(navigateUp) },
                OTP_TEXT_FIELD to { OTPTextFieldSample() },
                PROGRESS_INDICATOR to { ProgressIndicatorSample() },
                RADIO_BUTTON to { RadioButtonSample() },
//                SLIDER to { SliderSample() },
                SNACKBAR to { navigateUp -> SnackbarSample(navigateUp) },
                SCAFFOLD to { navigateUp -> ScaffoldSample(navigateUp) },
                SURFACE to { SurfaceSample() },
                SWITCH to { SwitchSample() },
                TOOLTIP to { TooltipSample() },
            )

    fun hasComponent(componentName: String): Boolean {
        return components.keys.any { it.label.equals(componentName, ignoreCase = true) }
    }
}
