package com.nomanr.lumo.utils

import java.io.File

object LinkFormatter {
    fun formatLink(rootDir: File, file: File): String {
        val url = "file:///$rootDir/$file".replace(" ", "%20")
        val fileName = file.name
        return "$fileName ($url)"
    }
}
