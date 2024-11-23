package com.nomanr.composeui.plugin.template

data class Template(
    val fileName: String,
    val requiredFiles: List<String> = emptyList(),
    val requiredContentPatterns: List<String> = emptyList()
)
