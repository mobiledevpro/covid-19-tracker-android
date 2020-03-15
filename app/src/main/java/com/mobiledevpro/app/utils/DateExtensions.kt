package com.mobiledevpro.app.utils

import java.text.SimpleDateFormat
import java.util.*

fun Long.dateToSting(): String {
    val dateFormat = SimpleDateFormat(" E, dd MMM yyyy HH:mm:ss z", Locale.getDefault())
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    return dateFormat.format(this)
}