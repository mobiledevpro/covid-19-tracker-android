package com.mobiledevpro.remote.implementation

import com.mobiledevpro.data.model.HtmlParserThrowableEntity
import com.mobiledevpro.data.model.statistic.CoordEntity
import com.mobiledevpro.data.model.statistic.CountyStatisticEntity
import com.mobiledevpro.data.model.statistic.DayStatisticEntity
import com.mobiledevpro.data.model.statistic.StatisticEntity
import com.mobiledevpro.data.repository.parcer.StatisticsParserHtml
import com.mobiledevpro.remote.mapper.toDateLong
import io.reactivex.Single
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

class DefaultStatisticsParserHtml : StatisticsParserHtml {

    override fun getConfirmedStatistics(): Single<ArrayList<StatisticEntity>> =
        Single.fromCallable { getDataStatistic(CONFIRMED_FILE_NAME) }

    override fun getDeathsStatistics(): Single<ArrayList<StatisticEntity>> =
        Single.fromCallable { getDataStatistic(DEATHS_FILE_NAME) }

    override fun getRecoveredStatistics(): Single<ArrayList<StatisticEntity>> =
        Single.fromCallable { getDataStatistic(RECOVERED_FILE_NAME) }


    private fun getDataStatistic(fileName: String): ArrayList<StatisticEntity> {

        val counties = ArrayList<StatisticEntity>()

        try {
            val docHtml: Document = Jsoup.connect("$BASE_URL$fileName").get()

            val titlesHtml = docHtml
                .getElementById("${LINE_IDENTIFICATION}1")
                .parent()
                .getElementsByTag(LINE_SEPARATOR)

            val countCountries = docHtml
                .getElementsByTag(ROW_IDENTIFICATION)
                .size

            for (i in 2 until countCountries) {

                val countryHtml = docHtml
                    .getElementById("$LINE_IDENTIFICATION${i}")
                    .parent()
                    .getElementsByTag(ROW_SEPARATOR)

                val countryName = countryHtml.toStringValue(2)

                var provinceName = countryHtml.toStringValue(1).apply {
                    when (this.isEmpty()) {
                        true -> countryName
                        else -> this
                    }
                }

                if (provinceName.isEmpty()) provinceName = countryName

                val countyStatisticEntity = CountyStatisticEntity(
                    provinceName = provinceName,
                    countryName = countryName
                )

                val coordEntity = CoordEntity(
                    lat = countryHtml.toDoubleValue(3),
                    long = countryHtml.toDoubleValue(4)
                )

                val dayCountsEntity = ArrayList<DayStatisticEntity>()

                for (j in 5 until countryHtml.size) {
                    dayCountsEntity.add(
                        DayStatisticEntity(
                            date = titlesHtml.toStringValue(j - 1).toDateLong(),
                            confirmed = if (fileName == CONFIRMED_FILE_NAME) countryHtml.toLongValue(j) else 0L,
                            deaths = if (fileName == DEATHS_FILE_NAME) countryHtml.toLongValue(j) else 0L,
                            recovered = if (fileName == RECOVERED_FILE_NAME) countryHtml.toLongValue(j) else 0L
                        )
                    )
                }

                counties.add(
                    StatisticEntity(
                        country = countyStatisticEntity,
                        coord = coordEntity,
                        dayStatistic = dayCountsEntity
                    )
                )
            }
        } catch (e: Exception) {
            throw HtmlParserThrowableEntity(
                "Parser Error: ${e.message}"
            )
        }

        return counties
    }

    private fun Elements.toStringValue(index: Int): String =
        if (this[index].childNodes().isEmpty()) ""
        else this[index].childNodes().first().toString()

    private fun Elements.toDoubleValue(index: Int): Double =
        if (this[index].childNodes().isEmpty()) 0.0
        else this[index].childNodes().first().toString().toDouble()

    private fun Elements.toLongValue(index: Int): Long =
        if (this[index].childNodes().isEmpty()) 0L
        else this[index].childNodes().first().toString().toLong()

    private companion object {

        const val BASE_URL =
//            "https://github.com/CSSEGISandData/COVID-19/tree/master/csse_covid_19_data/csse_covid_19_time_series/"

        "https://github.com/CSSEGISandData/COVID-19/blob/master/csse_covid_19_data/csse_covid_19_time_series/"

        const val CONFIRMED_FILE_NAME = "time_series_covid19_confirmed_global.csv"

        const val DEATHS_FILE_NAME = "time_series_covid19_deaths_global.csv"

        const val RECOVERED_FILE_NAME = "time_series_covid19_recovered_global.csv"

        const val LINE_IDENTIFICATION = "L"

        const val ROW_IDENTIFICATION = "tr"

        const val LINE_SEPARATOR = "th"

        const val ROW_SEPARATOR = "td"
    }
}
