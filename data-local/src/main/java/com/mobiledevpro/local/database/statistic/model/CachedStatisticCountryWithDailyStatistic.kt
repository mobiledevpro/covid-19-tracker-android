package com.mobiledevpro.local.database.statistic.model

import androidx.room.Embedded

class CachedStatisticCountryWithDailyStatistic {

    @Embedded
    var country: CachedStatisticCountry? = null

//    @Relation(
//        parentColumn = "country",
//        entityColumn = "country"
//    )
    var dayStatistic: List<CachedDayTotalCountryStatistic> = listOf()
}