package com.mobiledevpro.local.database.statistic.model

import androidx.room.Entity
import androidx.room.Index

/**
 * Data class for collect day statistic by country
 * @property province is parent name of country/province
 * @property date is a date of statistic
 * @property confirmed is a confirmed count people
 * @property deaths is a deaths of count people
 * @property recovered is a recovered count people
 * */
@Entity(
    tableName = "day_total_country_statistic",
    indices = [Index("date")],
    primaryKeys = ["date", "province"]
)
data class CachedDayTotalCountryStatistic(
    val province: String,
    val date: Long,
    val confirmed: Long,
    val deaths: Long,
    val recovered: Long
)