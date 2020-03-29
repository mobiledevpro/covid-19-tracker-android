package com.mobiledevpro.local.mapper

import com.mobiledevpro.data.model.CountryTotalEntity
import com.mobiledevpro.data.model.TotalEntity
import com.mobiledevpro.data.model.statistic.CoordEntity
import com.mobiledevpro.data.model.statistic.CountyStatisticEntity
import com.mobiledevpro.data.model.statistic.DayStatisticEntity
import com.mobiledevpro.data.model.statistic.StatisticEntity
import com.mobiledevpro.local.database.statistic.model.CachedDayTotalCountryStatistic
import com.mobiledevpro.local.database.statistic.model.CachedStatisticCountry
import com.mobiledevpro.local.database.statistic.model.CachedStatisticCountryWithDailyStatistic
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

fun CachedStatisticCountryWithDailyStatistic.toEntity() = StatisticEntity(
    country = CountyStatisticEntity(
        countryName = country!!.country,
        provinceName = country!!.province
    ),
    coord = CoordEntity(
        lat = country!!.latitude,
        long = country!!.longitude
    ),
    dayStatistic = dayStatistics.map { it.toEntity() }
)

private fun CachedDayTotalCountryStatistic.toEntity() = DayStatisticEntity(
    date = date,
    confirmed = confirmed,
    deaths = deaths,
    recovered = recovered
)