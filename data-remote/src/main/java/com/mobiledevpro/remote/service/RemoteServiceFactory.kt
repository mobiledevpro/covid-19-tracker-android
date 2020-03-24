package com.mobiledevpro.remote.service

import com.google.gson.GsonBuilder
import com.mobiledevpro.remote.service.api.FullStatisticRestApiClient
import com.mobiledevpro.remote.service.api.TotalRestApiClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RemoteServiceFactory(
    client: OkHttpClient
) {

    fun buildStackOverFlowApi(): RestApiClient = builder
        .baseUrl(BASE_URL)
    fun buildCovidDailyApi(): TotalRestApiClient = builder
        .baseUrl(BASE_DAILY_URL)
        .build()
        .create(TotalRestApiClient::class.java)

    fun buildCovidFullStatisticsApi(): FullStatisticRestApiClient = builder
        .baseUrl(BASE_FULL_STATISTICS_URL)
        .build()
        .create(FullStatisticRestApiClient::class.java)

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val builder = Retrofit.Builder()
        .client(client)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))

    companion object {

        private const val BASE_DAILY_URL =
            "https://services1.arcgis.com/0MSEUqKaxRlEPj5g/arcgis/rest/services/ncov_cases/FeatureServer/2/query/"

        private const val BASE_FULL_STATISTICS_URL =
            "https://services9.arcgis.com/N9p5hsImWXAccRNI/arcgis/rest/services/Nc2JKvYFoAEOFCG5JSI6/FeatureServer/4/query/"
    }
}
