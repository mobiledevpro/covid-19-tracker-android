package com.mobiledevpro.local.database

import android.content.Context
import com.mobiledevpro.data.model.CountryTotalEntity
import com.mobiledevpro.data.model.TotalEntity
import com.mobiledevpro.data.repository.userdata.TotalCovidCache
import com.mobiledevpro.local.database.model.CachedCounties
import com.mobiledevpro.local.database.model.CachedTotal
import com.mobiledevpro.local.mapper.toCached
import com.mobiledevpro.local.mapper.toEntity
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Database Helper
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 *
 * #MobileDevPro
 */
class DefaultTotalCovidCache(private val appContext: Context) : TotalCovidCache {

    override fun getTotalDataObservable(): Observable<TotalEntity> =
        AppDatabase.getInstance(appContext)
            .totalDataDao
            .getTotalDataObservable()
            .map(CachedTotal::toEntity)

    override fun updateTotalData(totalEntity: TotalEntity) = Completable
        .create { emitter ->
            val dao = AppDatabase.getInstance(appContext)
                .totalDataDao

            dao.deleteAllTotalValues()

            dao.insert(totalEntity.toCached())

            emitter.onComplete()
        }

    override fun getLocalCountriesObservable(query: String): Observable<List<CountryTotalEntity>> =
        AppDatabase.getInstance(appContext)
            .countiesDataDao
            .getCountiesDataObservable(query)
            .map { it.map(CachedCounties::toEntity) }

    override fun updateCountries(countriesTotalEntity: List<CountryTotalEntity>) = Completable
        .create { emitter ->
            val dao = AppDatabase.getInstance(appContext)
                .totalDataDao

            dao.deleteAllTotalValues()

            val countriesCached = countriesTotalEntity.map(CountryTotalEntity::toCached)
            AppDatabase.getInstance(appContext)
                .countiesDataDao
                .insert(countriesCached)

            emitter.onComplete()
        }
}
