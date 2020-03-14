package com.mobiledevpro.data.repository.userdata

import com.mobiledevpro.data.model.TotalEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface CovidCache {

    fun getTotalDataObservable(): Observable<TotalEntity>

    fun updateTotalData(totalEntity: TotalEntity): Completable
}