package com.mobiledevpro.domain.statistic.data

import com.mobiledevpro.domain.common.Result
import com.mobiledevpro.domain.model.StatisticCountry
import com.mobiledevpro.domain.model.TotalCountry
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface StatisticDataInteractor {

    fun getCountriesStatistics(page: Int): Single<List<TotalCountry>>

    fun fetchStatisticsFromHtml(): Completable

    fun observeStatisticByCountryName(query: String): Observable<Result<StatisticCountry>>
}