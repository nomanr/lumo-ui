package com.nomanr.lumo.plugin.template

data class Template(
    val componentFiles: List<String>,
    val supportingFiles: List<String> = emptyList(),
    val requirements: String? = null

) {

    fun allRequiredFiles(): Array<String> {
        return (componentFiles + supportingFiles).toTypedArray()
    }


}
