package com.mobiledevpro.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.mobiledevpro.local.database.model.CachedCounties
import io.reactivex.Observable

@Dao
internal interface CountryDataDao : BaseDao<CachedCounties> {

    @Query("SELECT * FROM counties WHERE country LIKE '%' || :query || '%' ORDER BY confirmed DESC")
    fun getCountiesDataObservable(query: String): Observable<List<CachedCounties>>

    @Query("DELETE FROM counties")
    fun deleteAllCountriesValues()
}