package com.mobiledevpro.data.repository.statistic

import com.mobiledevpro.data.mapper.toDomain
import com.mobiledevpro.data.model.statistic.CountryStatisticEntity
import com.mobiledevpro.domain.statistic.data.StatisticDataRepository

class DefaultStatisticDataRepository(
    private val statisticRemote: StatisticCovidRemote
) : StatisticDataRepository {

    override fun getStatisticFromApiByPage(page: Int) = statisticRemote
        .getStatisticByPage(page)
        .map { it.map(CountryStatisticEntity::toDomain) }
}