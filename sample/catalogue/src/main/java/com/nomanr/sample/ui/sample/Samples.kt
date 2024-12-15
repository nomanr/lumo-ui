package com.nomanr.sample.ui.sample

import androidx.compose.runtime.Composable
import com.nomanr.sample.ui.sample.ComponentId.ACCORDION
import com.nomanr.sample.ui.sample.ComponentId.ALERT_DIALOG
import com.nomanr.sample.ui.sample.ComponentId.BADGE
import com.nomanr.sample.ui.sample.ComponentId.BUTTON
import com.nomanr.sample.ui.sample.ComponentId.CARD
import com.nomanr.sample.ui.sample.ComponentId.CHECKBOX
import com.nomanr.sample.ui.sample.ComponentId.CHIP
import com.nomanr.sample.ui.sample.ComponentId.DIVIDER
import com.nomanr.sample.ui.sample.ComponentId.ICON
import com.nomanr.sample.ui.sample.ComponentId.ICON_BUTTON
import com.nomanr.sample.ui.sample.ComponentId.MODAL_BOTTOM_SHEET
import com.nomanr.sample.ui.sample.ComponentId.NAVIGATION_BAR
import com.nomanr.sample.ui.sample.ComponentId.OTP_TEXT_FIELD
import com.nomanr.sample.ui.sample.ComponentId.PROGRESS_INDICATOR
import com.nomanr.sample.ui.sample.ComponentId.RADIO_BUTTON
import com.nomanr.sample.ui.sample.ComponentId.RATING_BAR
import com.nomanr.sample.ui.sample.ComponentId.SCAFFOLD
import com.nomanr.sample.ui.sample.ComponentId.SLIDER
import com.nomanr.sample.ui.sample.ComponentId.SNACKBAR
import com.nomanr.sample.ui.sample.ComponentId.SURFACE
import com.nomanr.sample.ui.sample.ComponentId.SWITCH
import com.nomanr.sample.ui.sample.ComponentId.TEXT
import com.nomanr.sample.ui.sample.ComponentId.TEXT_FIELD
import com.nomanr.sample.ui.sample.ComponentId.TOOLTIP
import com.nomanr.sample.ui.sample.ComponentId.TOP_BAR
import com.nomanr.sample.ui.sample.samples.AccordionSample
import com.nomanr.sample.ui.sample.samples.AlertDialogSample
import com.nomanr.sample.ui.sample.samples.BadgeSample
import com.nomanr.sample.ui.sample.samples.ButtonSample
import com.nomanr.sample.ui.sample.samples.CardSample
import com.nomanr.sample.ui.sample.samples.CheckboxSample
import com.nomanr.sample.ui.sample.samples.ChipSample
import com.nomanr.sample.ui.sample.samples.DividerSample
import com.nomanr.sample.ui.sample.samples.IconButtonSample
import com.nomanr.sample.ui.sample.samples.IconSample
import com.nomanr.sample.ui.sample.samples.ModalBottomSheetSample
import com.nomanr.sample.ui.sample.samples.NavigationBarSample
import com.nomanr.sample.ui.sample.samples.OTPTextFieldSample
import com.nomanr.sample.ui.sample.samples.ProgressIndicatorSample
import com.nomanr.sample.ui.sample.samples.TextFieldSample
import com.nomanr.sample.ui.sample.samples.TextSample
import com.nomanr.sample.ui.sample.samples.TopBarSample
import kotlinx.serialization.Serializable

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
    RATING_BAR("Rating Bar"),
    SCAFFOLD("Scaffold"),
    SLIDER("Slider"),
    SNACKBAR("Snackbar"),
    SURFACE("Surface"),
    SWITCH("Switch"),
    TEXT("Text"),
    TEXT_FIELD("TextField"),
    TOOLTIP("Tooltip"),
    TOP_BAR("Top App Bar")
}

@Serializable
data class Component internal constructor(
    val id: ComponentId, val showTopBar: Boolean = true
) {

    val label: String
        get() = id.label

    companion object {
        private val components: List<Component> = listOf(
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
            Component(id = RATING_BAR),
            Component(id = SCAFFOLD),
            Component(id = SLIDER),
            Component(id = SNACKBAR),
            Component(id = SURFACE),
            Component(id = SWITCH),
            Component(id = TEXT),
            Component(id = TEXT_FIELD),
            Component(id = TOOLTIP),
            Component(id = TOP_BAR, showTopBar = false)
        )

        fun getAll(): List<Component> = components

        fun getById(id: ComponentId): Component = components.first { it.id == id }
    }
}


object Samples {
    val components: Map<ComponentId, @Composable ((() -> Unit)?) -> Unit>
        get() = mapOf<ComponentId, @Composable ((() -> Unit)?) -> Unit>(TEXT to { TextSample() },
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
            MODAL_BOTTOM_SHEET to { ModalBottomSheetSample() },
            NAVIGATION_BAR to { navigateUp -> NavigationBarSample(navigateUp) },
            OTP_TEXT_FIELD to { OTPTextFieldSample() },
            PROGRESS_INDICATOR to { ProgressIndicatorSample() })

    fun hasComponent(componentName: String): Boolean {
        return components.keys.any { it.label.equals(componentName, ignoreCase = true) }
    }
}