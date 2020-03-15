package com.mobiledevpro.domain.model

/**
 * Model for Countries list
 *
 * Created by Dmitriy Chernysh on Mar 15, 2020.
 *
 * http://androiddev.pro
 *
 */
data class Country(
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
