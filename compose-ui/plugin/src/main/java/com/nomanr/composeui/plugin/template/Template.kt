package com.nomanr.composeui.plugin.template

data class Template(
    val fileName: String,
    val requiredFiles: List<String> = emptyList(),

) {

    fun allRequiredFiles(): Array<String> {
        return arrayOf(fileName) + requiredFiles.toTypedArray()
    }


}
