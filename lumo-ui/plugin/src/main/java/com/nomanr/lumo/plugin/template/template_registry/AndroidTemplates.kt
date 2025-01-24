package com.nomanr.lumo.plugin.template.template_registry

import com.nomanr.lumo.plugin.template.template_registry.SupportedComponents.AlertDialog
import com.nomanr.lumo.plugin.template.template_registry.SupportedComponents.ModalBottomSheet
import com.nomanr.lumo.plugin.template.template_registry.SupportedComponents.Slider
import com.nomanr.lumo.plugin.template.template_registry.SupportedComponents.SystemBars
import com.nomanr.lumo.plugin.template.template_registry.SupportedComponents.Tooltip

object AndroidTemplates {
    private val alertDialog = Template(
        componentFiles = listOf("components/AlertDialog.kt.template"), supportingFiles = listOf(
            "foundation/Providers.kt.template",
        ),
        dependsOn = listOf(SupportedComponents.Button)
    )

    private val modalBottomSheet = Template(
        componentFiles = listOf("components/ModalBottomSheet.kt.template"),
        requirements = "Note: Add the following dependency to the project to use this component: \n" + "implementation(\"com.nomanr:composables:{version}\")\n" + "Reference: https://github.com/nomanr/compose-components",
    )

    private val slider = Template(
        componentFiles = listOf("components/Slider.kt.template"),
    )

    private val systemBars = Template(
        componentFiles = listOf("components/SystemBars.kt.template"),
    )

    private val toolTip = Template(
        componentFiles = listOf("components/Tooltip.kt.template")
    )


    fun getTemplates() = mapOf(
        AlertDialog to alertDialog,
        ModalBottomSheet to modalBottomSheet,
        Slider to slider,
        SystemBars to systemBars,
        Tooltip to toolTip,
    )


}