package com.mobiledevpro.local.mapper

import com.mobiledevpro.data.model.CountryTotalEntity
import com.mobiledevpro.data.model.TotalEntity
import com.mobiledevpro.data.model.statistic.StatisticEntity
import com.mobiledevpro.local.database.statistic.model.CachedStatisticCountry
import com.mobiledevpro.local.database.total.model.CachedTotal
import com.mobiledevpro.local.database.total.model.CachedTotalCounties

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

fun CachedTotalCounties.toEntity() = CountryTotalEntity(
    id = id,
    country = country,
    updated = updated,
    latitude = latitude,
    longitude = longitude,
    confirmed = confirmed,
    deaths = deaths,
    recovered = recovered,
    active = active
)

fun CountryTotalEntity.toCached() = CachedTotalCounties(
    id = id,
    country = country,
    updated = updated,
    latitude = latitude,
    longitude = longitude,
    confirmed = confirmed,
    deaths = deaths,
    recovered = recovered,
    active = active
)

fun StatisticEntity.toCache() = CachedStatisticCountry(
    province = country.provinceName,
    country = country.countryName,
    latitude = coord.lat,
    longitude = coord.long
)