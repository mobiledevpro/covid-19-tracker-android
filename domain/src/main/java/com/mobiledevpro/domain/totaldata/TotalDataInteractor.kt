package com.mobiledevpro.domain.totaldata

import com.mobiledevpro.domain.model.Country
import com.mobiledevpro.domain.model.Total
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Interactor for total values screen
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 *
 * #MobileDevPro
 */
interface TotalDataInteractor {

    fun observeTotalData(): Observable<Total>

    fun refreshTotalData(): Completable

    fun observeCountriesListData(query: String): Observable<ArrayList<Country>>

    fun refreshCountriesData(): Completable
}
