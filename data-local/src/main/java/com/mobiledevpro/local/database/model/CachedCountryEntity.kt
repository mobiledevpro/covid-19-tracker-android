package com.mobiledevpro.local.database.model

import androidx.room.Entity
import androidx.room.Index


@Entity(
    tableName = "country",
    indices = [Index(value = ["id"])],
    primaryKeys = ["id"]
)
data class CachedCountryEntity(

    var id: Int = 0,
    var name: String = "",
    var confirmed: Int = 0,
    var deaths: Int = 0,
    var recovered: Int = 0,
    var updateTime: Long = 0,
    var lat: Double = 0.0,
    var long: Double = 0.0
)