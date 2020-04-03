package com.mobiledevpro.data.repository.statistic

import com.mobiledevpro.data.model.statistic.StatisticEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface StatisticCovidCache {

    fun updateConfirmedData(statistics: ArrayList<StatisticEntity>): Completable

    fun observeConfirmedDataByCountryName(query: String): Observable<List<StatisticEntity>>
}