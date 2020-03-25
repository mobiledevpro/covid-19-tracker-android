package com.mobiledevpro.local.database.total

import com.mobiledevpro.data.model.CountryTotalEntity
import com.mobiledevpro.data.model.TotalEntity
import com.mobiledevpro.data.repository.userdata.TotalCovidCache
import com.mobiledevpro.local.database.AppDatabase
import com.mobiledevpro.local.database.total.model.CachedTotal
import com.mobiledevpro.local.database.total.model.CachedTotalCounties
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
class DefaultTotalCovidCache(private val database: AppDatabase) : TotalCovidCache {

    override fun getTotalDataObservable(): Observable<TotalEntity> =
        database
            .totalDataDao
            .getTotalDataObservable()
            .map(CachedTotal::toEntity)

    override fun updateTotalData(totalEntity: TotalEntity) = Completable
        .create { emitter ->
            val dao = database.totalDataDao

            dao.deleteAllTotalValues()

            dao.insert(totalEntity.toCached())

            emitter.onComplete()
        }

    override fun getLocalCountriesObservable(query: String): Observable<List<CountryTotalEntity>> =
        database
            .totalCountryDataDao
            .getCountiesDataObservable(query)
            .map { it.map(CachedTotalCounties::toEntity) }

    override fun updateCountries(countriesTotalEntity: List<CountryTotalEntity>) = Completable
        .create { emitter ->
            val dao = database.totalDataDao

            dao.deleteAllTotalValues()

            val countriesCached = countriesTotalEntity
                .map(CountryTotalEntity::toCached)

            database
                .totalCountryDataDao
                .insert(countriesCached)

            emitter.onComplete()
        }
}
