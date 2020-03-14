package com.mobiledevpro.local.database

import com.mobiledevpro.local.database.model.TotalDataEntity
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Database Helper
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 *
 * #MobileDevPro
 */
interface DatabaseHelper {

    fun getTotalDataObservable(): Observable<TotalDataEntity>

    fun updateTotalData(totalDataEntity: TotalDataEntity): Single<Boolean>
}
