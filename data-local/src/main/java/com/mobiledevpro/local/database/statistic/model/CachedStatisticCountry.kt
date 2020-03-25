package com.mobiledevpro.local.database.statistic.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/***
 * Data class for collect countries per static
 * @property province is a name of province or state
 * @property country is a name of country
 * @property latitude is a latitude by coordinate
 * @property longitude is a longitude by coordinate
 */
@Entity(
    tableName = "countries_statistic",
//    primaryKeys = ["id"],
    indices = [Index(value = ["country"], unique = false)]
)
data class CachedStatisticCountry(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val country: String,
    val province: String,
    val latitude: Double,
    val longitude: Double
)