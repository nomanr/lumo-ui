package com.nomanr.lumo.plugin.template.template_registry

import com.nomanr.lumo.plugin.template.template_registry.SupportedComponents.TestComponent
import com.nomanr.lumo.plugin.template.Template

object MultiplatformTemplates {
    private val testComponent = Template(
        componentFiles = listOf("components/TestComponent.kt.template"),
        platformSpecificFiles = mapOf(
            MultiplatformSourceSet.ANDROID to listOf("components/TestComponent.android.kt.template"),
            MultiplatformSourceSet.IOS to listOf("components/TestComponent.ios.kt.template"),
        )
    )

    fun getTemplate() = mapOf(
        TestComponent to testComponent,
    )

}



enum class MultiplatformSourceSet(val sourceSetName: String) {
    ANDROID("androidMain"),
    IOS("iosMain"),
    COMMON("commonMain")
}