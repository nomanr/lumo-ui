package com.nomanr.composeui.utils

import java.io.File

object LinkFormatter {
    fun formatLink(file: File): String {
        val absolutePath = file.absolutePath
        val url = "file://$absolutePath"
        val fileName = file.name
        return "$fileName ($url)"
    }
}