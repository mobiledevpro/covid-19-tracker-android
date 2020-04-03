package com.mobiledevpro.domain.model

/**
 * Data class for collect country statistic for view
 * @property country is a name of country
 * @property province is a name of province by country or by country
 * @property longitude is a longitude of coordinates by province
 * @property latitude is a latitude of coordinates by province
 * @property dayStatistics is a list of statistic for a day
 */
data class StatisticCountry(
    val country: String,
    val province: String,
    val latitude: Double,
    val longitude: Double,
    val dayStatistics: List<DayStatistic>
)

/**
 * Data class for collect statistic by day
 * @property date is a date of date in format 22/02/20
 * @property totalConfirmed is a confirmed count of people from start pandemic day to current day
 * @property totalDeaths is a deaths count of people from start pandemic day to current day
 * @property totalRecovered is a recovered count of people from start pandemic day to current day
 * @property confirmed is a confirmed count people by day
 * @property deaths is a deaths of count people by day
 * @property recovered is a recovered count people by day*/
data class DayStatistic(
    val date: Long,
    val totalConfirmed: Long,
    var confirmed: Long = 0L,
    val totalDeaths: Long,
    var deaths: Long = 0L,
    val totalRecovered: Long,
    var recovered: Long = 0L
)