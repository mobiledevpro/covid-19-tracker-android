package com.mobiledevpro.data.repository.statistic

import com.mobiledevpro.data.model.statistic.StatisticEntity
import io.reactivex.Completable

interface StatisticCovidCache {

    fun updateConfirmedData(statistics: ArrayList<StatisticEntity>): Completable

    fun updateDeathsData(statistics: ArrayList<StatisticEntity>): Completable

    fun updateRecoveredData(statistics: ArrayList<StatisticEntity>): Completable
}