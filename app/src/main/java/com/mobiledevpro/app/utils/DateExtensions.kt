package com.mobiledevpro.app.utils

import java.text.SimpleDateFormat
import java.util.*

fun Long.dateToSting(): String {
    val dateFormat = SimpleDateFormat("MMM dd, yyyy | hh:mm", Locale.getDefault())
    return dateFormat.format(this)
}

fun Long.toDayMonth(): String {
    if (this <= 0) return ""
    val dateFormat = SimpleDateFormat("MMM dd", Locale.getDefault())
    return dateFormat.format(this)
}