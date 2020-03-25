package com.mobiledevpro.local.database.statistic.model

import androidx.room.Embedded
import androidx.room.Relation

class CachedStatisticCountryWithDailyStatistic {

    @Embedded
    var country: CachedStatisticCountry? = null

    @Relation(
        parentColumn = "id",
        entityColumn = "date"
    )

    var dayStatistic: List<CachedDayTotalCountryStatistic> = listOf()
}