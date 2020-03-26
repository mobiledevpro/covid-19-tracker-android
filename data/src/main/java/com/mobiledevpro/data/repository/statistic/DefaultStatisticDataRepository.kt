package com.mobiledevpro.data.repository.statistic

import com.mobiledevpro.data.mapper.throwableToDomain
import com.mobiledevpro.data.mapper.toDomain
import com.mobiledevpro.data.model.statistic.CountryStatisticEntity
import com.mobiledevpro.data.model.statistic.CountyStatisticEntity
import com.mobiledevpro.data.model.statistic.DayStatisticEntity
import com.mobiledevpro.data.model.statistic.StatisticEntity
import com.mobiledevpro.data.repository.parcer.StatisticsParserHtml
import com.mobiledevpro.domain.model.StatisticCountry
import com.mobiledevpro.domain.statistic.data.StatisticDataRepository
import io.reactivex.Observable
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
        .throwableToDomain()

    override fun observeStatisticByCountyName(query: String): Observable<StatisticCountry> = statisticsCache
            //TODO: add zip for collect confirmed, deaths, recovered
        .observeConfirmedDataByCountryName(query)
        .map { result ->
            if (result.size <= 1) result[0]
            else collectDataByDay(result)
        }
        .map(StatisticEntity::toDomain)
        .throwableToDomain()

    private fun collectDataByDay(result: List<StatisticEntity>): StatisticEntity {
        val convertedDaysTotalEntity = ArrayList<DayStatisticEntity>()

        for (i in result[0].dayStatistic.indices) {
            var count = 0L

            for (j in result.indices) count += result[j].dayStatistic[i].count

            convertedDaysTotalEntity.add(
                DayStatisticEntity(
                    date = result[0].dayStatistic[i].date,
                    count = count
                )
            )
        }

        return StatisticEntity(
            country = CountyStatisticEntity(
                countryName = result[0].country.countryName,
                provinceName = result[0].country.countryName
            ),
            // TODO: need to think about getting coordinates for showing on Map
            coord = result[0].coord,
            dayStatistic = convertedDaysTotalEntity
        )

    }
}