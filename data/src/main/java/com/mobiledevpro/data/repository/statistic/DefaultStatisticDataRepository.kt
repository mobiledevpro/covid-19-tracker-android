package com.mobiledevpro.data.repository.statistic

import com.mobiledevpro.data.mapper.throwableToDomain
import com.mobiledevpro.data.mapper.toDomain
import com.mobiledevpro.data.model.statistic.CoordEntity
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
                val convertedStatisticCountry = ArrayList<StatisticEntity>()

                for (i in 0 until confirmed.size) {
                    val dayStatisticsEntity = ArrayList<DayStatisticEntity>()

                    for (j in confirmed[i].dayStatistic.indices) {
                        dayStatisticsEntity.add(
                            DayStatisticEntity(
                                date = confirmed[i].dayStatistic[j].date,
                                confirmed = confirmed[i].dayStatistic[j].confirmed,
                                deaths = deaths[i].dayStatistic[j].deaths,
                                recovered = recovered[i].dayStatistic[j].recovered
                            )
                        )
                    }

                    convertedStatisticCountry.add(
                        StatisticEntity(
                            CountyStatisticEntity(
                                provinceName = confirmed[i].country.provinceName,
                                countryName = confirmed[i].country.countryName
                            ),
                            CoordEntity(
                                lat = confirmed[i].coord.lat,
                                long = confirmed[i].coord.long
                            ),
                            dayStatistic = dayStatisticsEntity
                        )
                    )
                }

                convertedStatisticCountry
            }
        )
        .flatMapCompletable(statisticsCache::updateConfirmedData)
        .throwableToDomain()

    override fun observeStatisticByCountyName(query: String): Observable<StatisticCountry> =
        statisticsCache
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
            var confirmed = 0L
            var deaths = 0L
            var recovered = 0L

            for (j in result.indices) {
                confirmed += result[j].dayStatistic[i].confirmed
                deaths += result[j].dayStatistic[i].deaths
                recovered += result[j].dayStatistic[i].recovered
            }

            convertedDaysTotalEntity.add(
                DayStatisticEntity(
                    date = result[0].dayStatistic[i].date,
                    confirmed = confirmed,
                    deaths = deaths,
                    recovered = recovered
                )
            )
        }

        return StatisticEntity(
            country = CountyStatisticEntity(
                countryName = result[0].country.countryName,
                provinceName = result[0].country.countryName
            ),
            coord = result[0].coord,
            dayStatistic = convertedDaysTotalEntity
        )

    }
}