package com.mobiledevpro.local.database.statistic

import com.mobiledevpro.data.model.statistic.StatisticEntity
import com.mobiledevpro.data.repository.statistic.StatisticCovidCache
import com.mobiledevpro.local.database.AppDatabase
import com.mobiledevpro.local.database.statistic.model.CachedDayTotalCountryStatistic
import com.mobiledevpro.local.mapper.toCache
import io.reactivex.Completable

class DefaultStatisticsCovidCache(
    private val database: AppDatabase
) : StatisticCovidCache {

    override fun updateConfirmedData(statistics: ArrayList<StatisticEntity>) = Completable
        .defer {

            val countiesCache = statistics.map { it.toCache() }

            val dayStatisticsCache = statistics.map { statisticEntity ->
                statisticEntity.dayCounts.map { dayTotalEntity ->
                    CachedDayTotalCountryStatistic(
                        province = statisticEntity.country.provinceName,
                        country = statisticEntity.country.countryName,
                        date = dayTotalEntity.date,
                        count = dayTotalEntity.count
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
}
