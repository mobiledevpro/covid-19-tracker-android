package com.mobiledevpro.data.factory

import com.mobiledevpro.data.model.CountryTotalEntity
import com.mobiledevpro.data.model.TotalEntity


object TotalFactory {

    fun randomTotalEntity() = TotalEntity(
        confirmed = DataFactory.randomInt(),
        deaths = DataFactory.randomInt(),
        recovered = DataFactory.randomInt(),
        lastUpdateTime = DataFactory.randomLong()
    )

    fun randomCountryTotalEntity() = CountryTotalEntity(
        id = DataFactory.randomInt(),
        recovered = DataFactory.randomInt(),
        deaths = DataFactory.randomInt(),
        confirmed = DataFactory.randomInt(),
        active = DataFactory.randomInt(),
        country = DataFactory.randomString(),
        latitude = DataFactory.randomDouble(),
        longitude = DataFactory.randomDouble(),
        updated = DataFactory.randomLong()
    )
}