package com.mobiledevpro.app.utils

import java.text.SimpleDateFormat
import java.util.*

fun Long.dateToSting(): String {
    val dateFormat = SimpleDateFormat(" E, dd MMM yyyy hh:mm a", Locale.getDefault())
    return dateFormat.format(this)
}