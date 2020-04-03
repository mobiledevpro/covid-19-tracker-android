package com.mobiledevpro.remote.service.api

import com.mobiledevpro.remote.model.response.CountiesStatisticResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FullStatisticRestApiClient {

    @GET(".")
    fun getStatistic(
        @Query(value = "f", encoded = true) format: String = "json",
        @Query(value = "where", encoded = true) where: String = "Confirmed>0",
        @Query(value = "returnGeometry", encoded = true) geometry: Boolean = false,
        @Query(
            value = "spatialRel",
            encoded = true
        ) spatialRel: String = "esriSpatialRelIntersects",
        @Query(value = "outFields", encoded = true) outFields: String = "*",
        @Query(value = "orderByFields", encoded = true) orderedByFields: String = "Last_Update%20asc",
        @Query(value = "resultOffset", encoded = true) resultOffset: Int = 0,
        @Query(value = "resultRecordCount", encoded = true) resultRecordCount: Int = 1000,
        @Query(value = "cacheHint", encoded = true) cacheHint: Boolean = true
    ): Single<CountiesStatisticResponse>
}