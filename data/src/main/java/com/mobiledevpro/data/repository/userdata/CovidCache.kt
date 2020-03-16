package com.mobiledevpro.data.repository.userdata

import com.mobiledevpro.data.model.CountryEntity
import com.mobiledevpro.data.model.TotalEntity
import com.mobiledevpro.domain.model.Country
import io.reactivex.Completable
import io.reactivex.Observable

interface CovidCache {

    fun getTotalDataObservable(): Observable<TotalEntity>

    fun updateTotalData(totalEntity: TotalEntity): Completable

    fun getLocalCountriesObservable(): Observable<List<CountryEntity>>

    fun updateCountries(countriesEntity: List<CountryEntity>): Completable
}