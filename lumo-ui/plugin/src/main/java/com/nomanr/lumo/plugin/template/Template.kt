package com.nomanr.lumo.plugin.template

enum class SupportedPlatforms(val label: String) {
    ANDROID("ANDROID"),
    IOS("IOS")
}

data class Template(
    val componentFiles: List<String>,
    val supportingFiles: List<String> = emptyList(),
    val multiplatformFiles: Map<SupportedPlatforms, List<String>> = emptyMap(),
    val requirements: String? = null
) {

    fun allRequiredFiles(): Array<String> {
        val allMultiplatformFiles = multiplatformFiles.flatMap { it.value }
        return (componentFiles + supportingFiles + allMultiplatformFiles).toTypedArray()
    }


}
