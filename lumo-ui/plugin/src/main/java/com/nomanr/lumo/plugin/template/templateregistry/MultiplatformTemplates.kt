package com.nomanr.lumo.plugin.template.templateregistry

// import com.nomanr.lumo.plugin.template.template_registry.SupportedComponents.TestComponent

object MultiplatformTemplates {
    private val testComponent =
        Template(
            componentFiles = listOf("components/TestComponent.kt.template"),
            platformSpecificFiles =
                mapOf(
                    MultiplatformSourceSet.ANDROID to listOf("components/TestComponent.android.kt.template"),
                    MultiplatformSourceSet.IOS to listOf("components/TestComponent.ios.kt.template"),
                ),
        )

    fun getTemplate() = emptyMap<SupportedComponents, Template>()
}

enum class MultiplatformSourceSet(val sourceSetName: String) {
    ANDROID("androidMain"),
    IOS("iosMain"),
    COMMON("commonMain"),
}
