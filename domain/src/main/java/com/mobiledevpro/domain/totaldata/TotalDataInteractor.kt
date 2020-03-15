package com.mobiledevpro.domain.totaldata

import com.mobiledevpro.domain.model.Country
import com.mobiledevpro.domain.model.Total
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

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

    fun observeCountriesListData(): Observable<List<Country>>

    fun refreshCountriesData(): Completable
}
