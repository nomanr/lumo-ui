package com.nomanr.composeui.plugin.utils

object Logger {

    private const val RESET = "\u001B[0m"
    private const val RED = "\u001B[31m"
    private const val GREEN = "\u001B[32m"
    private const val BLUE = "\u001B[34m"
    private const val YELLOW = "\u001B[33m"

    fun error(message: String) {
        println("$RED[ERROR] $message$RESET")
    }

    fun success(message: String) {
        println("$GREEN[SUCCESS] $message$RESET")
    }

    fun info(message: String) {
        println("$BLUE[INFO] $message$RESET")
    }

    fun warn(message: String) {
        println("$YELLOW[WARN] $message$RESET")
    }
}