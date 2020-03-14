package com.mobiledevpro.local.database

import android.content.Context
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
class DatabaseHelperImpl(private val appContext: Context) : DatabaseHelper {

    override fun getTotalDataObservable(): Observable<TotalDataEntity> =
            AppDatabase.getInstance(appContext)
                    .totalDataDao
                    .getTotalDataObservable()

    override fun updateTotalData(totalDataEntity: TotalDataEntity): Single<Boolean> {
        return Single.create { emitter ->

            AppDatabase.getInstance(appContext)
                    .totalDataDao
                    .insert(totalDataEntity)

            emitter.onSuccess(true)
        }
    }
}
