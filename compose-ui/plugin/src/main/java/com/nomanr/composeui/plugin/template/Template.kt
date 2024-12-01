package com.nomanr.composeui.plugin.template

data class Template(
    val fileName: String,
    val requiredFiles: List<String> = emptyList(),
    val requirements: String? = null

) {

    fun allRequiredFiles(): Array<String> {
        return arrayOf(fileName) + requiredFiles.toTypedArray()
    }


}
