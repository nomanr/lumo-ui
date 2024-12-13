package com.nomanr.sample.ui.sample

import androidx.compose.runtime.Composable
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
import com.nomanr.sample.ui.sample.samples.TextFieldSamples
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
            Component(id = ComponentId.ACCORDION),
            Component(id = ComponentId.ALERT_DIALOG),
            Component(id = ComponentId.BADGE, showTopBar = false),
            Component(id = ComponentId.BUTTON),
            Component(id = ComponentId.CARD),
            Component(id = ComponentId.CHECKBOX),
            Component(id = ComponentId.CHIP),
            Component(id = ComponentId.DIVIDER),
            Component(id = ComponentId.ICON),
            Component(id = ComponentId.ICON_BUTTON),
            Component(id = ComponentId.MODAL_BOTTOM_SHEET),
            Component(id = ComponentId.NAVIGATION_BAR),
            Component(id = ComponentId.OTP_TEXT_FIELD),
            Component(id = ComponentId.PROGRESS_INDICATOR),
            Component(id = ComponentId.RADIO_BUTTON),
            Component(id = ComponentId.RATING_BAR),
            Component(id = ComponentId.SCAFFOLD),
            Component(id = ComponentId.SLIDER),
            Component(id = ComponentId.SNACKBAR),
            Component(id = ComponentId.SURFACE),
            Component(id = ComponentId.SWITCH),
            Component(id = ComponentId.TEXT),
            Component(id = ComponentId.TEXT_FIELD),
            Component(id = ComponentId.TOOLTIP),
            Component(id = ComponentId.TOP_BAR, showTopBar = false)
        )

        fun getAll(): List<Component> = components

        fun getById(id: ComponentId): Component = components.first { it.id == id }
    }
}


object Samples {
    val components: Map<ComponentId, @Composable ((() -> Unit)?) -> Unit>
        get() = mapOf<ComponentId, @Composable ((() -> Unit)?) -> Unit>(
            ComponentId.TEXT to { TextSample() },
            ComponentId.BUTTON to { ButtonSample() },
            ComponentId.CHECKBOX to { CheckboxSample() },
            ComponentId.CHIP to { ChipSample() },
            ComponentId.DIVIDER to { DividerSample()},
            ComponentId.ICON to { IconSample() },
            ComponentId.ICON_BUTTON to { IconButtonSample() },
            ComponentId.CARD to { CardSample() },
            ComponentId.TOP_BAR to { navigateUp -> TopBarSample(navigateUp) },
            ComponentId.ACCORDION to { AccordionSample() },
            ComponentId.TEXT_FIELD to { TextFieldSamples() },
            ComponentId.ALERT_DIALOG to { AlertDialogSample() },
            ComponentId.BADGE to { navigateUp -> BadgeSample(navigateUp) },
        )

    fun hasComponent(componentName: String): Boolean {
        return components.keys.any { it.label.equals(componentName, ignoreCase = true) }
    }
}