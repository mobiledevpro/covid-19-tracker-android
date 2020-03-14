package com.mobiledevpro.data

import com.mobiledevpro.domain.model.Total
import com.mobiledevpro.local.database.model.TotalDataEntity

/**
 * Extensions for mapping data level models to domain level models and vise versa
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 *
 */

fun TotalDataEntity.toTotal(): Total = Total(
        confirmed = confirmed,
        deaths = deaths,
        recovered = recovered,
        updateTime = lastUpdateTime
)

fun Total.toEntity(): TotalDataEntity = TotalDataEntity(
        id = 0,
        confirmed = confirmed,
        deaths = deaths,
        recovered = recovered,
        lastUpdateTime = updateTime
)
