package com.mobiledevpro.data.repository.userdata

import com.mobiledevpro.data.model.CountryTotalEntity
import com.mobiledevpro.data.model.TotalEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface TotalCovidCache {

    fun getTotalDataObservable(): Observable<TotalEntity>

    fun updateTotalData(totalEntity: TotalEntity): Completable

    fun getLocalCountriesObservable(query: String): Observable<List<CountryTotalEntity>>

    fun updateCountries(countriesTotalEntity: List<CountryTotalEntity>): Completable
}