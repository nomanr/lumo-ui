package com.nomanr.composeui.utils

import java.io.File

object LinkFormatter {
    fun formatLink(rootDir: File, file: File): String {
        val url = "file:///$rootDir/$file"
        val fileName = file.name
        return "$fileName ($url)"
    }
}
