package com.mobiledevpro.data.repository.statistic

import com.mobiledevpro.data.model.statistic.CountryStatisticEntity
import io.reactivex.Single

interface StatisticCovidRemote {

    fun getStatisticByPage(page: Int): Single<List<CountryStatisticEntity>>
}