package com.mobiledevpro.data.repository.statistic

import com.mobiledevpro.data.mapper.toDomain
import com.mobiledevpro.data.model.statistic.CountryStatisticEntity
import com.mobiledevpro.data.model.statistic.StatisticEntity
import com.mobiledevpro.data.repository.parcer.StatisticsParserHtml
import com.mobiledevpro.domain.statistic.data.StatisticDataRepository
import io.reactivex.Single
import io.reactivex.functions.Function3

class DefaultStatisticDataRepository(
    private val statisticsCache: StatisticCovidCache,
    private val statisticRemote: StatisticCovidRemote,
    private val statisticsParserHtml: StatisticsParserHtml
) : StatisticDataRepository {

    override fun getStatisticFromApiByPage(page: Int) = statisticRemote
        .getStatisticByPage(page)
        .map { it.map(CountryStatisticEntity::toDomain) }

    override fun fetchStatisticsFromHtml() = Single
        .zip<ArrayList<StatisticEntity>, ArrayList<StatisticEntity>, ArrayList<StatisticEntity>, ArrayList<StatisticEntity>>(
            statisticsParserHtml.getConfirmedStatistics(),
            statisticsParserHtml.getDeathsStatistics(),
            statisticsParserHtml.getRecoveredStatistics(),
            Function3 { confirmed, deaths, recovered ->
                confirmed
            }
        )
        .flatMapCompletable(statisticsCache::updateConfirmedData)
    //TODO: add error implementation

}