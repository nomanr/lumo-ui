
package com.nomanr.composeui.utils

class Logger {

    private val RESET = "\u001B[0m"
    private val RED = "\u001B[31m"
    private val GREEN = "\u001B[32m"
    private val BLUE = "\u001B[34m"
    private val YELLOW = "\u001B[33m"
    private val CYAN = "\u001B[36m"
    private val MAGENTA = "\u001B[35m"
    private val WHITE = "\u001B[37m"

    fun formatAsErrorMessage(message: String): String {
        return "$RED$message$RESET"
    }

    fun formatAsWarningMessage(message: String): String {
        return "$YELLOW$message$RESET"
    }

    fun formatAsSuccessMessage(message: String): String {
        return "$GREEN$message$RESET"
    }

    fun formatAsInfoMessage(message: String): String {
        return "$BLUE$message$RESET"
    }

    fun formatAsDebugMessage(message: String): String {
        return "$CYAN$message$RESET"
    }

    fun formatAsCriticalErrorMessage(message: String): String {
        return "$MAGENTA$message$RESET"
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
    }
}