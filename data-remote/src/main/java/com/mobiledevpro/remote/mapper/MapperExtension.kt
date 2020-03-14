package com.mobiledevpro.remote.mapper

import com.mobiledevpro.data.model.TotalValueEntity
import com.mobiledevpro.remote.model.response.TotalResponse

fun TotalResponse.toEntity() = TotalValueEntity(
    count = feature?.first()?.attribute?.value ?: -1
)