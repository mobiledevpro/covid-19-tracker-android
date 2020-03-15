package com.mobiledevpro.remote.implementation

import com.mobiledevpro.data.model.CountryEntity
import com.mobiledevpro.data.repository.userdata.CovidRemote
import com.mobiledevpro.remote.mapper.toEntity
import com.mobiledevpro.remote.model.response.CountryResponse
import com.mobiledevpro.remote.model.response.TotalResponse
import com.mobiledevpro.remote.service.api.RestApiClient
import io.reactivex.Single

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

    override fun getCountries(): Single<List<CountryEntity>> = api
        .getCountries()
        .map { it.countries.first().attribute }
        .map { it.map(CountryResponse::toEntity) }
}