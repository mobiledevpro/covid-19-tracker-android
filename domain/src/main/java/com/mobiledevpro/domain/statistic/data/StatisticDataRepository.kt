package com.mobiledevpro.domain.statistic.data

import com.mobiledevpro.domain.model.Country
import io.reactivex.Completable
import io.reactivex.Single

interface StatisticDataRepository {

    fun getStatisticFromApiByPage(page: Int): Single<List<Country>>

    fun fetchStatisticsFromHtml(): Completable
}