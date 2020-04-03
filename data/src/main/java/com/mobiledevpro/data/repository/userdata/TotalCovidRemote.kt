package com.mobiledevpro.data.repository.userdata

import com.mobiledevpro.data.model.CountryTotalEntity
import com.mobiledevpro.data.model.TotalValueEntity
import io.reactivex.Single

interface TotalCovidRemote {

    fun getTotalConfirmed(): Single<TotalValueEntity>

    fun getTotalDeaths(): Single<TotalValueEntity>

    fun getTotalRecovered(): Single<TotalValueEntity>

    fun getCountries(): Single<List<CountryTotalEntity>>
}