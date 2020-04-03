package com.mobiledevpro.domain.totaldata

import com.mobiledevpro.domain.model.Total
import com.mobiledevpro.domain.model.TotalCountry
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single


/**
 * Repository for Total data screen
 *
 * NOTE: See implementation in the data module
 *
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 *
 * #MobileDevPro
 */
interface TotalDataRepository {

    fun getLocalTotalDataObservable(): Observable<Total>

    fun setLocalTotalData(total: Total): Completable

    fun getTotalData(): Single<Total>

    fun getLocalCountriesObservable(query: String): Observable<List<TotalCountry>>

    fun getCountries(): Single<List<TotalCountry>>

    fun setLocalCountriesData(totalCountries: List<TotalCountry>): Completable
}
