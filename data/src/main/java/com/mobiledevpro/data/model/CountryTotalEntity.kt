package com.mobiledevpro.data.model

data class CountryTotalEntity(
    val id: Int,
    val country: String,
    val updated: Long,
    val latitude: Double,
    val longitude: Double,
    val confirmed: Int,
    val deaths: Int,
    val recovered: Int,
    val active: Int
)