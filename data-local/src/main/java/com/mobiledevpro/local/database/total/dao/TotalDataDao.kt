package com.mobiledevpro.local.database.total.dao

import androidx.room.Dao
import androidx.room.Query
import com.mobiledevpro.local.database.total.model.CachedTotal
import io.reactivex.Observable

@Dao
interface TotalDataDao : BaseDao<CachedTotal> {

    @Query("SELECT * FROM total")
    fun getTotalDataObservable(): Observable<CachedTotal>

    @Query("DELETE FROM total")
    fun deleteAllTotalValues()
}