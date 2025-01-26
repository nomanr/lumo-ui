package com.nomanr.lumo.utils

class Logger {
    private val red = "\u001B[31m"
    private val green = "\u001B[32m"
    private val blue = "\u001B[34m"
    private val yellow = "\u001B[33m"
    private val cyan = "\u001B[36m"
    private val magenta = "\u001B[35m"
    private val white = "\u001B[37m"

    fun formatAsErrorMessage(message: String): String {
        return "$red$message${Companion.RESET}"
    }

    fun formatAsWarningMessage(message: String): String {
        return "$yellow$message${Companion.RESET}"
    }

    fun formatAsSuccessMessage(message: String): String {
        return "$green$message${Companion.RESET}"
    }

    fun formatAsInfoMessage(message: String): String {
        return "$blue$message${Companion.RESET}"
    }

    fun formatAsDebugMessage(message: String): String {
        return "$cyan$message${Companion.RESET}"
    }

    fun formatAsCriticalErrorMessage(message: String): String {
        return "$magenta$message${Companion.RESET}"
    }

    fun error(message: String) {
        println(formatAsErrorMessage(message))
    }

    fun success(message: String) {
        println(formatAsSuccessMessage(message))
    }

    fun info(message: String) {
        println(formatAsInfoMessage(message))
    }

    fun warn(message: String) {
        println(formatAsWarningMessage(message))
    }

    fun debug(message: String) {
        println(formatAsDebugMessage(message))
    }

    fun criticalError(message: String) {
        println(formatAsCriticalErrorMessage(message))
    }

    fun blankLine() {
        println()
    }

    companion object {
        val logger by lazy { Logger() }

        fun getInstance(): Logger {
            return logger
        }

        private const val RESET = "\u001B[0m"
    }
}
