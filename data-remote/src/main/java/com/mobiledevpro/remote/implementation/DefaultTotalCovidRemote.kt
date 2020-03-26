package com.mobiledevpro.remote.implementation

import com.mobiledevpro.data.model.CountryTotalEntity
import com.mobiledevpro.data.repository.userdata.TotalCovidRemote
import com.mobiledevpro.remote.mapper.toEntity
import com.mobiledevpro.remote.model.response.TotalResponse
import com.mobiledevpro.remote.service.api.TotalRestApiClient
import io.reactivex.Single

class DefaultTotalCovidRemote(
    private val apiTotal: TotalRestApiClient
) : TotalCovidRemote {

    override fun getTotalConfirmed() = apiTotal
        .getTotalConfirmed()
        .map(TotalResponse::toEntity)

    override fun getTotalDeaths() = apiTotal
        .getTotalDeaths()
        .map(TotalResponse::toEntity)

    override fun getTotalRecovered() = apiTotal
        .getTotalRecovered()
        .map(TotalResponse::toEntity)

    override fun getCountries(): Single<List<CountryTotalEntity>> = apiTotal
        .getCountries()
        .map { it.countries }
        .map {
            it.map { countryResponse ->
                countryResponse.attribute.toEntity()
            }
        }
}