package com.mobiledevpro.local.database.total.dao

import androidx.room.Dao
import androidx.room.Query
import com.mobiledevpro.local.database.total.model.CachedTotalCounties
import io.reactivex.Observable

@Dao
interface TotalCountryDataDao : BaseDao<CachedTotalCounties> {

    @Query("SELECT * FROM counties_total WHERE country LIKE '%' || :query || '%' ORDER BY confirmed DESC")
    fun getCountiesDataObservable(query: String): Observable<List<CachedTotalCounties>>

    @Query("DELETE FROM counties_total")
    fun deleteAllCountriesValues()
}