package com.mobiledevpro.remote.implementation

import com.mobiledevpro.data.repository.statistic.StatisticCovidRemote
import com.mobiledevpro.remote.mapper.toEntity
import com.mobiledevpro.remote.service.api.FullStatisticRestApiClient

class DefaultStatisticCovidRemote(
    private val apiStatistic: FullStatisticRestApiClient
) : StatisticCovidRemote {

    override fun getStatisticByPage(page: Int) = apiStatistic
        .getStatistic(resultOffset = page)
        .map { it.countries }
        .map {
            it.map { countryResponse ->
                countryResponse.attribute.toEntity()
            }
        }
}