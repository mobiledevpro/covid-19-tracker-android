package com.mobiledevpro.data.repository.userdata

import com.mobiledevpro.data.mapper.throwableToDomain
import com.mobiledevpro.data.mapper.toCacheEntity
import com.mobiledevpro.data.mapper.toDomain
import com.mobiledevpro.data.mapper.toEntity
import com.mobiledevpro.data.model.CountryTotalEntity
import com.mobiledevpro.data.model.TotalValueEntity
import com.mobiledevpro.domain.model.Total
import com.mobiledevpro.domain.model.TotalCountry
import com.mobiledevpro.domain.totaldata.TotalDataRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Function3

/**
 * Repository for Total data screen
 *
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 *
 * #MobileDevPro
 */
class DefaultTotalDataRepository(
    private val totalCovidCache: TotalCovidCache,
    private val totalCovidRemote: TotalCovidRemote
) : TotalDataRepository {

    override fun getLocalTotalDataObservable(): Observable<Total> = totalCovidCache
        .getTotalDataObservable()
        .throwableToDomain()

    override fun setLocalTotalData(total: Total): Completable = Single
        .just(total)
        .map(Total::toCacheEntity)
        .flatMapCompletable(totalCovidCache::updateTotalData)
        .throwableToDomain()

    override fun getTotalData(): Single<Total> = Single
        .zip<TotalValueEntity, TotalValueEntity, TotalValueEntity, Total>(
            totalCovidRemote.getTotalConfirmed(),
            totalCovidRemote.getTotalDeaths(),
            totalCovidRemote.getTotalRecovered(),
            Function3 { countConfirmed, countDeaths, countRecovered ->
                Total(
                    confirmed = countConfirmed.count,
                    deaths = countDeaths.count,
                    recovered = countRecovered.count
                )
            })
        .throwableToDomain()


    override fun getLocalCountriesObservable(query: String): Observable<List<TotalCountry>> = totalCovidCache
        .getLocalCountriesObservable(query)
        .map { it.map(CountryTotalEntity::toDomain) }
        .throwableToDomain()

    override fun getCountries(): Single<List<TotalCountry>> = totalCovidRemote
        .getCountries()
        .map { it.map(CountryTotalEntity::toDomain) }
        .throwableToDomain()

    override fun setLocalCountriesData(totalCountries: List<TotalCountry>): Completable = Single
        .just(totalCountries)
        .map { it.map(TotalCountry::toEntity) }
        .flatMapCompletable(totalCovidCache::updateCountries)
        .throwableToDomain()
}

