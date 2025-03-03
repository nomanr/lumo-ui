package com.nomanr.lumo.plugin.template.templateregistry

import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.Slider
import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.Surface
import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.SystemBars
import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.Tooltip

object AndroidTemplates {
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
            dependsOn = listOf(Surface),
        )

    fun getTemplates() =
        mapOf(
            Slider to slider,
            SystemBars to systemBars,
            Tooltip to toolTip,
        )
}
