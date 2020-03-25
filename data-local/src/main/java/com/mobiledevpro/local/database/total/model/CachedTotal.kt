package com.mobiledevpro.local.database.total.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "total")
data class CachedTotal(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var confirmed: Int = 0,

    var deaths: Int = 0,

    var recovered: Int = 0,

    var lastUpdateTime: Long = 0
)