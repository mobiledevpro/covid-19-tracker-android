package com.mobiledevpro.data.mapper

import com.mobiledevpro.data.model.TotalEntity
import com.mobiledevpro.domain.model.Total

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
        recovered = recovered
)

fun TotalEntity.toDomain() = Total(
        confirmed = confirmed,
        deaths = deaths,
        recovered = recovered
)
