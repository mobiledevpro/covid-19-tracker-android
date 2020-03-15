package com.mobiledevpro.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.mobiledevpro.local.database.model.CachedCounties
import io.reactivex.Observable

@Dao
internal interface CountryDataDao : BaseDao<CachedCounties> {

    @Query("SELECT * FROM counties")
    fun getCountiesDataObservable(): Observable<List<CachedCounties>>

    @Query("DELETE FROM counties")
    fun deleteAllCountriesValues()
}