package com.mobiledevpro.local.database.statistic.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.mobiledevpro.local.database.statistic.model.CachedStatisticCountry
import com.mobiledevpro.local.database.statistic.model.CachedStatisticCountryWithDailyStatistic
import com.mobiledevpro.local.database.total.dao.BaseDao
import io.reactivex.Observable

@Dao
interface StatisticCountryDataDao : BaseDao<CachedStatisticCountry> {

    @Query("SELECT * FROM countries_statistic")
    fun getCountries(): Observable<List<CachedStatisticCountry>>

    @Transaction
    @Query("SELECT * FROM countries_statistic WHERE country = :country AND province = :province")
    fun getCountryByCountryName(province: String, country: String): Observable<CachedStatisticCountryWithDailyStatistic>
}