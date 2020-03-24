package com.mobiledevpro.domain.totaldata

import com.mobiledevpro.domain.common.None
import com.mobiledevpro.domain.common.Result
import com.mobiledevpro.domain.extension.toResult
import com.mobiledevpro.domain.model.Country
import com.mobiledevpro.domain.model.Total
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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

    override fun observeTotalData(): Observable<Result<Total>> = totalDataRepository
        .getLocalTotalDataObservable()
        .toResult()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())

    override fun refreshTotalData(): Single<Result<None>> = totalDataRepository
        .getTotalData()
        .flatMapCompletable(totalDataRepository::setLocalTotalData)
        .toResult()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())

    override fun observeCountriesListData(
        query: String
    ): Observable<Result<ArrayList<Country>>> = totalDataRepository
        .getLocalCountriesObservable(query)
        .map { ArrayList(it) }
        .toResult()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())

    override fun refreshCountriesData(): Single<Result<None>> = totalDataRepository
        .getCountries()
        .flatMapCompletable(totalDataRepository::setLocalCountriesData)
        .toResult()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
}