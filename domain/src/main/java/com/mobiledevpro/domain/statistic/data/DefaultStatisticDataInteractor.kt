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

    override fun observeStatisticByCountryName(query: String): Observable<Result<StatisticCountry>> = statisticDataRepository
        .observeStatisticByCountyName(query)
        .map { result ->
            for (i in result.dayStatistics.indices)
                if (i == 0) {
                    result.dayStatistics[i].recovered = result.dayStatistics[i].totalRecovered
                    result.dayStatistics[i].deaths = result.dayStatistics[i].totalDeaths
                    result.dayStatistics[i].confirmed = result.dayStatistics[i].totalConfirmed
                }
                else {
                    result.dayStatistics[i].confirmed =
                        result.dayStatistics[i].totalConfirmed - result.dayStatistics[i - 1].confirmed
                    result.dayStatistics[i].deaths =
                        result.dayStatistics[i].totalDeaths - result.dayStatistics[i - 1].deaths
                    result.dayStatistics[i].confirmed =
                        result.dayStatistics[i].totalRecovered - result.dayStatistics[i - 1].recovered
                }
            result
        }
        .toResult()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
}