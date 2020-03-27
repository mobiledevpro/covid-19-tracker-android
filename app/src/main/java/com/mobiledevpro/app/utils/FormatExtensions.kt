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
