package com.mobiledevpro.remote.service.api

import com.mobiledevpro.remote.model.ApiGetTotalDataNetwork
import com.mobiledevpro.remote.model.response.CountriesResponse
import com.mobiledevpro.remote.model.response.TotalResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface for Rest API client
 *
 *
 * Created by Dmitriy V. Chernysh
 *
 *
 * https://instagr.am/mobiledevpro
 * #MobileDevPro
 */
interface RestApiClient {

    @GET(".")
    fun getTotalConfirmed(
        @Query(value = "f", encoded = true) format: String = "json",
        @Query(value = "where", encoded = true) where: String = "Confirmed>0",
        @Query(value = "returnGeometry", encoded = true) geometry: Boolean = false,
        @Query(
            value = "spatialRel",
            encoded = true
        ) spatialRel: String = "esriSpatialRelIntersects",
        @Query(value = "outFields", encoded = true) outFields: String = "*",
        @Query(
            value = "outStatistics",
            encoded = true
        ) outStatistics: String = "[{\"statisticType\":\"sum\",\"onStatisticField\":\"Confirmed\",\"outStatisticFieldName\":\"value\"}]",
        @Query(value = "outSR", encoded = true) outSR: Int = 102100,
        @Query(value = "cacheHint", encoded = true) cacheHint: Boolean = true
    ): Single<TotalResponse>

    @GET(".")
    fun getTotalDeaths(
        @Query(value = "f", encoded = true) format: String = "json",
        @Query(value = "where", encoded = true) where: String = "Confirmed>0",
        @Query(value = "returnGeometry", encoded = true) geometry: Boolean = false,
        @Query(
            value = "spatialRel",
            encoded = true
        ) spatialRel: String = "esriSpatialRelIntersects",
        @Query(value = "outFields", encoded = true) outFields: String = "*",
        @Query(
            value = "outStatistics",
            encoded = true
        ) outStatistics: String = "[{\"statisticType\":\"sum\",\"onStatisticField\":\"Deaths\",\"outStatisticFieldName\":\"value\"}]",
        @Query(value = "outSR", encoded = true) outSR: Int = 102100,
        @Query(value = "cacheHint", encoded = true) cacheHint: Boolean = true
    ): Single<TotalResponse>

    @GET(".")
    fun getTotalRecovered(
        @Query(value = "f", encoded = true) format: String = "json",
        @Query(value = "where", encoded = true) where: String = "Confirmed>0",
        @Query(value = "returnGeometry", encoded = true) geometry: Boolean = false,
        @Query(
            value = "spatialRel",
            encoded = true
        ) spatialRel: String = "esriSpatialRelIntersects",
        @Query(value = "outFields", encoded = true) outFields: String = "*",
        @Query(
            value = "outStatistics",
            encoded = true
        ) outStatistics: String = "[{\"statisticType\":\"sum\",\"onStatisticField\":\"Recovered\",\"outStatisticFieldName\":\"value\"}]",
        @Query(value = "outSR", encoded = true) outSR: Int = 102100,
        @Query(value = "cacheHint", encoded = true) cacheHint: Boolean = true
    ): Single<TotalResponse>

    @GET(".")
    fun getCountriesList(
        @Query(value = "f", encoded = true) format: String = "json",
        @Query(value = "where", encoded = true) where: String = "Confirmed>0",
        @Query(value = "returnGeometry", encoded = true) geometry: Boolean = false,
        @Query(
            value = "spatialRel",
            encoded = true
        ) spatialRel: String = "esriSpatialRelIntersects",
        @Query(value = "outFields", encoded = true) outFields: String = "*",
        @Query(value = "orderByFields", encoded = true) orderByFields: String = "Confirmed%20desc",
        @Query(value = "outSR", encoded = true) outSR: Int = 102100,
        @Query(value = "resultOffset", encoded = true) resultOffset: Int = 0,
        @Query(value = "resultRecordCount", encoded = true) resultRecordCount: Int = 100,
        @Query(value = "cacheHint", encoded = true) cacheHint: Boolean = true
    ): Single<CountriesResponse>

}