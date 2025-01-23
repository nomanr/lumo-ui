package com.nomanr.lumo.plugin.template.template_registry

data class Template(
    val componentFiles: List<String>,
    val supportingFiles: List<String> = emptyList(),
    val dependsOn: List<SupportedComponents> = emptyList(),
    val platformSpecificFiles : Map<MultiplatformSourceSet, List<String>> = emptyMap(),
    val platformSpecificSupportingFiles : Map<MultiplatformSourceSet, List<String>> = emptyMap(),
    val requirements: String? = null,
) {

    fun allFiles(): Array<String> {
        return (componentFiles + supportingFiles).toTypedArray()
    }
}
