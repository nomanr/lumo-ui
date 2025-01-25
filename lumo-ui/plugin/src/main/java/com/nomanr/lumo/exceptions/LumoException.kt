package com.nomanr.lumo.exceptions

private const val RESET = "\u001B[0m"
private const val RED = "\u001B[31m"

class LumoException(message: String) : Exception("${RED}Lumo UI Plugin: $message$RESET") {
    override fun toString(): String {
        return message ?: super.toString()
    }
}
