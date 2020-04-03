package com.mobiledevpro.domain.statistic.data

import com.mobiledevpro.domain.common.Result
import com.mobiledevpro.domain.extension.toResult
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

    override fun observeStatisticByCountryName(query: String): Observable<Result<StatisticCountry>> =
        statisticDataRepository
            .observeStatisticByCountyName(query)
            .map { result ->
                result.dayStatistics.mapIndexed { index, dayStatistic ->
                    if (index == 0) {
                        dayStatistic.confirmed = result.dayStatistics[index].totalConfirmed
                        dayStatistic.deaths = result.dayStatistics[index].totalDeaths
                        dayStatistic.recovered = result.dayStatistics[index].totalRecovered
                    } else {
                        dayStatistic.confirmed =
                            dayStatistic.totalConfirmed - result.dayStatistics[index - 1].totalConfirmed
                        dayStatistic.deaths =
                            dayStatistic.totalDeaths - result.dayStatistics[index - 1].totalDeaths
                        dayStatistic.recovered =
                            dayStatistic.totalRecovered - result.dayStatistics[index - 1].totalRecovered
                    }
                }

                result
            }
            .toResult()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}