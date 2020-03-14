package com.mobiledevpro.local.mapper

import com.mobiledevpro.data.model.TotalEntity
import com.mobiledevpro.local.database.model.CachedTotal

fun CachedTotal.toEntity() = TotalEntity(
    confirmed = confirmed,
    deaths = deaths,
    recovered = recovered,
    lastUpdateTime = lastUpdateTime
)

fun TotalEntity.toCached() = CachedTotal(
    confirmed = confirmed,
    deaths = deaths,
    recovered = recovered,
    lastUpdateTime = lastUpdateTime
)