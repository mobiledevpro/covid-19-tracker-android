package com.mobiledevpro.domain.totaldata

import com.mobiledevpro.domain.common.None
import com.mobiledevpro.domain.common.Result
import com.mobiledevpro.domain.model.Total
import com.mobiledevpro.domain.model.TotalCountry
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

    fun observeTotalData(): Observable<Result<Total>>

    fun refreshTotalData(): Single<Result<None>>

    fun observeCountriesListData(query: String): Observable<Result<ArrayList<TotalCountry>>>

    fun refreshCountriesData(): Single<Result<None>>
}
