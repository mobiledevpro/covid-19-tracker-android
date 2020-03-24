package com.mobiledevpro.data.model.statistic

/**
 * Data class describe statistic by country with dates and count of people
 * @property country is a country with descriptions
 * @property coord is a coordinates with a latitude and longitude
 * @property dayCounts is a list of people counts by date
 */
data class StatisticEntity(
    val country: CountyStatisticEntity,
    val coord: CoordEntity,
    val dayCounts: List<DayTotalEntity>
)

/**
 * Data class describe country data
 * @property provinceName is a Province/State
 * @property countryName is a Country/Region
 */
data class CountyStatisticEntity(
    val provinceName: String,
    val countryName: String
)

/**
 * Data class for location coordinates
 * @property lat is a latitude
 * @property long is a longitude
 */
data class CoordEntity(
    val lat: Double,
    val long: Double
)

/**
 * Data class for day people total count by date
 * @property date is a date in format like 22/03/20
 * @property count is a count people
 */
data class DayTotalEntity(
    val date: String,
    val count: Long
)
