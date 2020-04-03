package com.mobiledevpro.remote.mapper

import com.mobiledevpro.data.model.CountryTotalEntity
import com.mobiledevpro.data.model.TotalValueEntity
import com.mobiledevpro.data.model.statistic.CountryStatisticEntity
import com.mobiledevpro.remote.model.response.CountryStatisticResponse
import com.mobiledevpro.remote.model.response.CountryTotalResponse
import com.mobiledevpro.remote.model.response.TotalResponse
import java.text.SimpleDateFormat
import java.util.*

fun TotalResponse.toEntity() = TotalValueEntity(
    count = feature.first().attribute.value
)

fun CountryTotalResponse.toEntity() = CountryTotalEntity(
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

fun CountryStatisticResponse.toEntity() = CountryStatisticEntity(
    id = id,
    country = country,
    updated = updated,
    confirmed = confirmed,
    deaths = deaths,
    deltaConfirmed = deltaConfirmed
)

fun String.toDateLong(): Long {
    val dateFormat = SimpleDateFormat("MM/dd/yy", Locale.getDefault())
    val date = dateFormat.parse(this)
    return date.time
}