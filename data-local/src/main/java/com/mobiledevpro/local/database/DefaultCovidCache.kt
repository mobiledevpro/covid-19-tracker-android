package com.mobiledevpro.local.database

import android.content.Context
import com.mobiledevpro.data.model.CountryEntity
import com.mobiledevpro.data.model.TotalEntity
import com.mobiledevpro.data.repository.userdata.CovidCache
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
class DefaultCovidCache(private val appContext: Context) : CovidCache {

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

    override fun getLocalCountriesObservable(query: String): Observable<List<CountryEntity>> =
        AppDatabase.getInstance(appContext)
            .countiesDataDao
            .getCountiesDataObservable(query)
            .map { it.map(CachedCounties::toEntity) }

    override fun updateCountries(countriesEntity: List<CountryEntity>) = Completable
        .create { emitter ->
            val dao = AppDatabase.getInstance(appContext)
                .totalDataDao

            dao.deleteAllTotalValues()

            val countriesCached = countriesEntity.map(CountryEntity::toCached)
            AppDatabase.getInstance(appContext)
                .countiesDataDao
                .insert(countriesCached)

            emitter.onComplete()
        }
}
