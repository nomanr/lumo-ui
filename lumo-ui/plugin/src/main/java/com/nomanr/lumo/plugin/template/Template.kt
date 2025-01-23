package com.nomanr.lumo.plugin.template

import com.nomanr.lumo.plugin.template.template_registry.MultiplatformSourceSet
import com.nomanr.lumo.plugin.template.template_registry.SupportedComponents

data class Template(
    val componentFiles: List<String>,
    val supportingFiles: List<String> = emptyList(),
    val dependsOn: List<SupportedComponents> = emptyList(),
    val requirements: String? = null,
    val platformSpecificFiles : Map<MultiplatformSourceSet, List<String>> = emptyMap()
) {

    fun allFiles(): Array<String> {
        return (componentFiles + supportingFiles).toTypedArray()
    }
}
