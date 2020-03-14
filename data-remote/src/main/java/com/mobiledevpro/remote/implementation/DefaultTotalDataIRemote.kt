package com.mobiledevpro.remote.implementation

import com.mobiledevpro.data.repository.userdata.CovidRemote
import com.mobiledevpro.remote.service.api.RestApiClient
import com.mobiledevpro.remote.mapper.toEntity
import com.mobiledevpro.remote.model.response.TotalResponse

class DefaultTotalDataIRemote(
    private val api: RestApiClient
) : CovidRemote {

    override fun getTotalConfirmed() = api
        .getTotalConfirmed()
        .map(TotalResponse::toEntity)

    override fun getTotalDeaths() = api
        .getTotalDeaths()
        .map(TotalResponse::toEntity)

    override fun getTotalRecovered() = api
        .getTotalRecovered()
        .map(TotalResponse::toEntity)
}