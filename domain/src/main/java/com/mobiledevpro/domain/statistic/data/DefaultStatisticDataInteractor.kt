package com.mobiledevpro.domain.statistic.data

import com.mobiledevpro.domain.model.StatisticCountry
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DefaultStatisticDataInteractor(
    private val statisticDataRepository: StatisticDataRepository
) : StatisticDataInteractor {

    override fun getCountriesStatistics(page: Int) = statisticDataRepository
        .getStatisticFromApiByPage(page)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())

    override fun fetchStatisticsFromHtml() = statisticDataRepository
        .fetchStatisticsFromHtml()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())

    override fun observeStatisticByCountryName(query: String): Observable<StatisticCountry> = statisticDataRepository
        .observeStatisticByCountyName(query)
        .map<StatisticCountry> { result ->
            for (i in result.dayStatistics.indices)
                if (i == 0) result.dayStatistics[i].dayCount = result.dayStatistics[i].totalCount
                else {
                    result.dayStatistics[i].dayCount =
                        result.dayStatistics[i].totalCount - result.dayStatistics[i - 1].totalCount
                }
            result
        }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
}