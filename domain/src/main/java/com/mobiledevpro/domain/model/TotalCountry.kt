package com.mobiledevpro.domain.model

/**
 * Model for Countries list
 *
 * Created by Dmitriy Chernysh on Mar 15, 2020.
 *
 * http://androiddev.pro
 *
 */
data class TotalCountry(
    val id: Int = 0,
    val country: String = "",
    val updated: Long = 0,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val confirmed: Int = 0,
    val deaths: Int = 0,
    val recovered: Int = 0,
    val active: Int = 0
)
