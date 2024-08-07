package com.ndc.moviekuh.utils

fun String.isNumber(): Boolean {
    val regex = "^[0-9]+\$".toRegex()
    return this.matches(regex)
}