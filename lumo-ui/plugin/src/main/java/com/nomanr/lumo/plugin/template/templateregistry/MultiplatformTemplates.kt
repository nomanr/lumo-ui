package com.nomanr.lumo.plugin.template.templateregistry

import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.Surface
import com.nomanr.lumo.plugin.template.templateregistry.SupportedComponents.Tooltip

object MultiplatformTemplates {
    private val tooltip =
        Template(
            componentFiles =
                listOf(
                    "components/Tooltip.kt.template",
                ),
            dependsOn = listOf(Surface),
            platformSpecificFiles =
                mapOf(
                    MultiplatformSourceSet.ANDROID to listOf("components/Tooltip.android.kt.template"),
                    MultiplatformSourceSet.IOS to listOf("components/Tooltip.ios.kt.template"),
                ),
        )

    fun getTemplate() =
        mapOf(
            Tooltip to tooltip,
        )
}

enum class MultiplatformSourceSet(val sourceSetName: String) {
    ANDROID("androidMain"),
    IOS("iosMain"),
    COMMON("commonMain"),
    MACOS("macosMain"),
    DESKTOP("desktopMain"),
}
