package com.mobiledevpro.domain.statistic.data

import com.mobiledevpro.domain.model.StatisticCountry
import com.mobiledevpro.domain.model.TotalCountry
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface StatisticDataRepository {

    fun getStatisticFromApiByPage(page: Int): Single<List<TotalCountry>>

    fun fetchStatisticsFromHtml(): Completable

    fun observeStatisticByCountyName(query: String): Observable<StatisticCountry>
}