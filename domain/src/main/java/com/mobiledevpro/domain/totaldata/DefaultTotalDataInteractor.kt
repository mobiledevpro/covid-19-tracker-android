package com.mobiledevpro.domain.totaldata

import com.mobiledevpro.domain.model.Country
import com.mobiledevpro.domain.model.Total
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList

/**
 * Interactor for UserEdit screen
 *
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 *
 * #MobileDevPro
 */
class DefaultTotalDataInteractor(
    private val totalDataRepository: TotalDataRepository
) : TotalDataInteractor {

    override fun observeTotalData(): Observable<Total> = totalDataRepository
        .getLocalTotalDataObservable()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())

    override fun refreshTotalData(): Completable = totalDataRepository
        .getTotalData()
        .flatMapCompletable(totalDataRepository::setLocalTotalData)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())

    override fun observeCountriesListData(query: String): Observable<ArrayList<Country>> =
        totalDataRepository
            .getLocalCountriesObservable(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    override fun refreshCountriesData(): Completable = totalDataRepository
        .getCountries()
        .flatMapCompletable(totalDataRepository::setLocalCountriesData)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
}