package com.nomanr.lumo.plugin.template.templateregistry

import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.ModalBottomSheet
import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.Slider
import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.SystemBars
import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.Tooltip

object AndroidTemplates {
    private val modalBottomSheet =
        Template(
            componentFiles = listOf("components/ModalBottomSheet.kt.template"),
            requirements =
                "Note: Add the following dependency to the project to use this component: \n" +
                    "implementation(\"com.nomanr:composables:{version}\")\n" +
                    "Reference: https://github.com/nomanr/compose-components",
        )

    private val slider =
        Template(
            componentFiles = listOf("components/Slider.kt.template"),
        )

    private val systemBars =
        Template(
            componentFiles = listOf("components/SystemBars.kt.template"),
        )

    private val toolTip =
        Template(
            componentFiles = listOf("components/Tooltip.kt.template"),
        )

    fun getTemplates() =
        mapOf(
            ModalBottomSheet to modalBottomSheet,
            Slider to slider,
            SystemBars to systemBars,
            Tooltip to toolTip,
        )
}
