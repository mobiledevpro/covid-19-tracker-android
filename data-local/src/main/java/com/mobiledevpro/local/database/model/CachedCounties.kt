package com.mobiledevpro.local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "counties")
data class CachedCounties(

    @PrimaryKey
    var id: Int = 0,

    var country: String = "",

    var updated: Long = 0,

    var latitude: Long = 0,

    var longitude: Long = 0,

    var confirmed: Int = 0,

    var deaths: Int = 0,

    var recovered: Int = 0,

    var active: Int = 0
)