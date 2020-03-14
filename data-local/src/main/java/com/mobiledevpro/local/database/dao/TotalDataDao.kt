package com.mobiledevpro.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.mobiledevpro.local.database.model.CachedTotal
import io.reactivex.Observable

@Dao
internal interface TotalDataDao : BaseDao<CachedTotal> {

    @Query("SELECT * FROM total")
    fun getTotalDataObservable(): Observable<CachedTotal>

}