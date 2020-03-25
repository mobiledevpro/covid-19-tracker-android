package com.mobiledevpro.domain.model

/**
 * Data class for collect country statistic for view
 * @property country is a name of country
 * @property province is a name of province by country or by country
 * @property longitude is a longitude of coordinates by province
 * @property latitude is a latitude of coordinates by province
 * @property dayStatistic is a list of statistic for a day
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
 * @property totalCount is a count of people from start pandemic day to current day
 * @property dayCount is a count of people by current day
 */
data class DayStatistic(
    val date: String,
    val totalCount: Long,
    var dayCount: Long = 0L
)