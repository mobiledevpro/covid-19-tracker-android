package com.mobiledevpro.domain.totaldata

import com.mobiledevpro.domain.model.Total
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

    fun setLocalTotalData(total: Total): Single<Boolean>

    fun createNetworkRequestTotal(outStatisticsId: Int): Single<ApiGetTotalDataNetwork.Request>
}
