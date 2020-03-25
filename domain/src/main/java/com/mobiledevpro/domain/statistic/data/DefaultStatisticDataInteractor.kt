package com.mobiledevpro.domain.statistic.data

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
}