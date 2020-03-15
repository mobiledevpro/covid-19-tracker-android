package com.mobiledevpro.remote.mapper

import com.mobiledevpro.data.model.CountryEntity
import com.mobiledevpro.data.model.TotalValueEntity
import com.mobiledevpro.remote.model.response.CountryResponse
import com.mobiledevpro.remote.model.response.TotalResponse

fun TotalResponse.toEntity() = TotalValueEntity(
    count = feature?.first()?.attribute?.value ?: -1
)

fun CountryResponse.toEntity() = CountryEntity(
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