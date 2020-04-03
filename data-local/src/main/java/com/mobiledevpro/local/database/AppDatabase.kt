package com.mobiledevpro.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mobiledevpro.local.BuildConfig
import com.mobiledevpro.local.database.statistic.dao.StatisticCountryDataDao
import com.mobiledevpro.local.database.statistic.dao.StatisticDayCountryDataDao
import com.mobiledevpro.local.database.statistic.model.CachedDayTotalCountryStatistic
import com.mobiledevpro.local.database.statistic.model.CachedStatisticCountry
import com.mobiledevpro.local.database.total.dao.TotalCountryDataDao
import com.mobiledevpro.local.database.total.dao.TotalDataDao
import com.mobiledevpro.local.database.total.model.CachedTotal
import com.mobiledevpro.local.database.total.model.CachedTotalCounties

/**
 * Room Database
 *
 * Created by Dmitriy Chernysh on 11/12/19.
 *
 * http://androiddev.pro
 *
 * #MobileDevPro
 */

@Database(
    entities = [
        CachedTotal::class,
        CachedTotalCounties::class,
        CachedStatisticCountry::class,
        CachedDayTotalCountryStatistic::class
    ],
    version = BuildConfig.RoomDatabaseVersion,
    exportSchema = true
)

abstract class AppDatabase : RoomDatabase() {
    abstract val totalDataDao: TotalDataDao
    abstract val totalCountryDataDao: TotalCountryDataDao
    abstract val statisticCountryData: StatisticCountryDataDao
    abstract val statisticDayCountryData: StatisticDayCountryDataDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}

