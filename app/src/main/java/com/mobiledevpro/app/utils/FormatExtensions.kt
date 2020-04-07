package com.mobiledevpro.app.utils

import java.text.DecimalFormat

fun Int.toDecimalFormat(): String {
    if (this <= 0) return "0"
    val formatter = DecimalFormat("#,###,###")
    return formatter.format(this)
}

fun Long.toDecimalFormat(): String {
    if (this <= 0) return "0"
    val formatter = DecimalFormat("#,###,###")
    return formatter.format(this)
}

fun Float.toNumberWithAbbreviation(): String =
    when {
        this > 999 -> "${DecimalFormat("#.#").format(this / 1000)}K"
        this > 999999 -> "${DecimalFormat("#.#").format(this / 1000000)}M"
        this > 999999999 -> "${DecimalFormat("#.#").format(this / 1000000000)}B"
        else -> "${this.toInt()}"
    }
