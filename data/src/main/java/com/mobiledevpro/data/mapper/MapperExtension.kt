package com.mobiledevpro.data.mapper

import com.mobiledevpro.data.model.CountryTotalEntity
import com.mobiledevpro.data.model.TotalEntity
import com.mobiledevpro.data.model.statistic.CountryStatisticEntity
import com.mobiledevpro.domain.model.Country
import com.mobiledevpro.domain.model.Total
import java.util.*

/**
 * Extensions for mapping data level models to domain level models and vise versa
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 *
 */

fun Total.toCacheEntity() = TotalEntity(
        confirmed = confirmed,
        deaths = deaths,
        recovered = recovered,
        lastUpdateTime = if (updateTime <= 0) Date().time else updateTime
)

fun TotalEntity.toDomain() = Total(
        confirmed = confirmed,
        deaths = deaths,
        recovered = recovered,
        updateTime = lastUpdateTime
)

fun CountryTotalEntity.toDomain() = Country(
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

fun Country.toEntity() = CountryTotalEntity(
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

fun CountryStatisticEntity.toDomain() = Country(
    id = id,
    country = country,
    updated = updated,
    confirmed = confirmed.toInt(),
    deaths = deaths.toInt()
)