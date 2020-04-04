package com.mobiledevpro.domain.statistic.data

import com.mobiledevpro.domain.common.None
import com.mobiledevpro.domain.common.Result
import com.mobiledevpro.domain.model.StatisticCountry
import com.mobiledevpro.domain.model.TotalCountry
import io.reactivex.Observable
import io.reactivex.Single

interface StatisticDataInteractor {

    fun getCountriesStatistics(page: Int): Single<List<TotalCountry>>

    fun fetchStatisticsFromHtml(): Single<Result<None>>

    fun observeStatisticByCountryName(query: String): Observable<Result<StatisticCountry>>
}