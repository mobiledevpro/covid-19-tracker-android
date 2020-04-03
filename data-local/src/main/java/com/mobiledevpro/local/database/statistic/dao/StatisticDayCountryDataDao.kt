package com.mobiledevpro.local.database.statistic.dao

import androidx.room.Dao
import androidx.room.Query
import com.mobiledevpro.local.database.statistic.model.CachedDayTotalCountryStatistic
import com.mobiledevpro.local.database.total.dao.BaseDao
import io.reactivex.Observable

@Dao
interface StatisticDayCountryDataDao : BaseDao<CachedDayTotalCountryStatistic> {

    @Query("SELECT * FROM day_total_country_statistic")
    fun getDayStatistics(): Observable<List<CachedDayTotalCountryStatistic>>
}