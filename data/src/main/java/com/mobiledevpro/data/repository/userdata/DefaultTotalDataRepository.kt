package com.mobiledevpro.data.repository.userdata

import com.mobiledevpro.data.mapper.toCacheEntity
import com.mobiledevpro.data.mapper.toDomain
import com.mobiledevpro.data.mapper.toEntity
import com.mobiledevpro.data.model.CountryTotalEntity
import com.mobiledevpro.data.model.TotalEntity
import com.mobiledevpro.domain.model.Country
import com.mobiledevpro.domain.model.Total
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
        .map(TotalEntity::toDomain)
        .onErrorReturn { Total() }

    override fun setLocalTotalData(total: Total): Completable = Single
        .just(total)
        .map(Total::toCacheEntity)
        .flatMapCompletable(totalCovidCache::updateTotalData)

    override fun getTotalData(): Single<Total> = Single
        .zip(
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

    override fun getLocalCountriesObservable(query: String): Observable<ArrayList<Country>> =
        totalCovidCache
            .getLocalCountriesObservable(query)
            .map { it.map(CountryTotalEntity::toDomain) }
            .flatMap { list -> Observable.just(ArrayList(list)) }
            .onErrorReturn { ArrayList() }

    override fun getCountries(): Single<List<Country>> = totalCovidRemote
        .getCountries()
        .map { it.map(CountryTotalEntity::toDomain) }

    override fun setLocalCountriesData(countries: List<Country>): Completable = Single
        .just(countries)
        .map { it.map(Country::toEntity) }
        .flatMapCompletable(totalCovidCache::updateCountries)
}

