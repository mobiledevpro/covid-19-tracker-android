package com.mobiledevpro.domain.totaldata

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
class TotalDataInteractorImpl(private val totalDataRepository: TotalDataRepository) : TotalDataInteractor {

    override fun observeTotalData(): Observable<Total> =
            totalDataRepository.getLocalTotalDataObservable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())

    // TODO: 3/14/20 get data from network and save to local database
    override fun refreshTotalData(): Single<Boolean> =
            getTestTotalDataNetwork()
                    .flatMap(totalDataRepository::setLocalTotalData)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())


    //only for debug
    private fun getTestTotalDataNetwork(): Single<Total> =
            Single.create {
                //for debugging
                val total = Total(
                        144863,
                        5398,
                        70249,
                        1584097775000
                )

                it.onSuccess(total)
            }

}