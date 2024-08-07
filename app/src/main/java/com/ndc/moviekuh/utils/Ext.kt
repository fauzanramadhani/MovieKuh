package com.ndc.moviekuh.utils

import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun String.isNumber(): Boolean {
    val regex = "^[0-9]+\$".toRegex()
    return this.matches(regex)
}

fun Double.format(format: String = "%.1f") = String.format(format, this)

fun Float.format(format: String = "%.1f") = String.format(format, this)

fun String?.encode(): String? = if (this == null) null else URLEncoder.encode(this, StandardCharsets.UTF_8.toString())

fun String?.decode(): String? = if (this == null) null else URLDecoder.decode(this, StandardCharsets.UTF_8.toString())
