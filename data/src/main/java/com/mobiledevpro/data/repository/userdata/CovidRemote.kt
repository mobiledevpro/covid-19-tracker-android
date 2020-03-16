package com.mobiledevpro.data.repository.userdata

import com.mobiledevpro.data.model.CountryEntity
import com.mobiledevpro.data.model.TotalValueEntity
import io.reactivex.Single

interface CovidRemote {

    fun getTotalConfirmed(): Single<TotalValueEntity>

    fun getTotalDeaths(): Single<TotalValueEntity>

    fun getTotalRecovered(): Single<TotalValueEntity>

    fun getCountries(): Single<List<CountryEntity>>
}