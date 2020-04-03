package com.mobiledevpro.data.model.statistic

data class CountryStatisticEntity(
    val id: Int,
    val country: String,
    val updated: Long,
    val confirmed: Long,
    val deaths: Long,
    val deltaConfirmed: Long
)