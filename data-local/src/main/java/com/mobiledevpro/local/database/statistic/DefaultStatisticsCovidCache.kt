package com.mobiledevpro.local.database.statistic

import com.mobiledevpro.data.model.statistic.StatisticEntity
import com.mobiledevpro.data.repository.statistic.StatisticCovidCache
import com.mobiledevpro.local.database.AppDatabase
import com.mobiledevpro.local.database.statistic.model.CachedDayTotalCountryStatistic
import com.mobiledevpro.local.database.statistic.model.CachedStatisticCountryWithDailyStatistic
import com.mobiledevpro.local.mapper.toCache
import com.mobiledevpro.local.mapper.toEntity
import io.reactivex.Completable
import io.reactivex.Observable

class DefaultStatisticsCovidCache(
    private val database: AppDatabase
) : StatisticCovidCache {

    override fun updateConfirmedData(statistics: ArrayList<StatisticEntity>) = Completable
        .defer {

            val countiesCache = statistics.map { it.toCache() }

            val dayStatisticsCache = statistics.map { statisticEntity ->
                statisticEntity.dayStatistic.map { dayTotalEntity ->
                    CachedDayTotalCountryStatistic(
                        province = statisticEntity.country.provinceName,
                        date = dayTotalEntity.date,
                        confirmed = dayTotalEntity.confirmed,
                        deaths = dayTotalEntity.deaths,
                        recovered = dayTotalEntity.recovered
                    )
                }
            }

            database.statisticCountryData.insert(countiesCache)

            database.statisticDayCountryData.insert(dayStatisticsCache.flatten())

            Completable.complete()
        }

    override fun updateDeathsData(statistics: ArrayList<StatisticEntity>) = Completable
        .defer {
            Completable.complete()
        }

    override fun updateRecoveredData(statistics: ArrayList<StatisticEntity>) = Completable
        .defer {
            Completable.complete()
        }

    override fun observeConfirmedDataByCountryName(query: String): Observable<List<StatisticEntity>> = database
        .statisticCountryData.getCountryByCountryName(query)
        .map { it.map(CachedStatisticCountryWithDailyStatistic::toEntity) }
}
