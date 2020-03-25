package com.mobiledevpro.local.database.total.model

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "counties_total",
    indices = [
        Index(value = ["id"]),
        Index(value = ["country"])
    ],
    primaryKeys = ["id"]
)
data class CachedTotalCounties(

    var id: Int = 0,

    var country: String = "",

    var updated: Long = 0,

    var latitude: Double = 0.0,

    var longitude: Double = 0.0,

    var confirmed: Int = 0,

    var deaths: Int = 0,

    var recovered: Int = 0,

    var active: Int = 0
)