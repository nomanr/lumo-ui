package com.nomanr.composeui.exceptions

private val RESET = "\u001B[0m"
private val RED = "\u001B[31m"

class ComposeUIException(message: String) : Exception("${RED}Compose UI Plugin: $message$RESET") {
    constructor(message: String, cause: Throwable) : this("$message\n${cause.message}")

    override fun toString(): String {
        return message ?: super.toString()
    }
}
