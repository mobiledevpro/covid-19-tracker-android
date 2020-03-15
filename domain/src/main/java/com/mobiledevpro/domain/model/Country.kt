package com.mobiledevpro.domain.model

data class Country(
    val id: Int,
    val country: String,
    val updated: Long,
    val latitude: Long,
    val longitude: Long,
    val confirmed: Int,
    val deaths: Int,
    val recovered: Int,
    val active: Int
)