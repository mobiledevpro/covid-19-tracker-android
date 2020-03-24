package com.mobiledevpro.data.repository.userdata

import com.mobiledevpro.data.repository.parcer.StatisticsParserHtml

class DefaultStatisticDataRepository(
    private val statisticsParserHtml: StatisticsParserHtml
) {

    fun fetchStatisticsFromHtml() = statisticsParserHtml
        .getConfirmedStatistics()
}