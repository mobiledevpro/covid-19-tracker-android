package com.mobiledevpro.data.model

data class TotalEntity(
    val confirmed: Int,
    val deaths: Int,
    val recovered: Int,
    var lastUpdateTime: Long = 0
)