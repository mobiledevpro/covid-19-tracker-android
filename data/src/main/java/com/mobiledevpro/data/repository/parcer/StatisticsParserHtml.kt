package com.mobiledevpro.data.repository.parcer

import com.mobiledevpro.data.model.statistic.StatisticEntity
import io.reactivex.Single

interface StatisticsParserHtml {

    fun getConfirmedStatistics(): Single<ArrayList<StatisticEntity>>

    fun getDeathsStatistics(): Single<ArrayList<StatisticEntity>>

    fun getRecoveredStatistics(): Single<ArrayList<StatisticEntity>>
}