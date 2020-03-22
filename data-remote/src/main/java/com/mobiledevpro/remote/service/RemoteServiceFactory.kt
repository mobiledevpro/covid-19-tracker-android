package com.mobiledevpro.remote.service

import com.google.gson.GsonBuilder
import com.mobiledevpro.remote.service.api.RestApiClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RemoteServiceFactory(
    client: OkHttpClient
) {

    fun buildStackOverFlowApi(): RestApiClient = builder
        .baseUrl(BASE_URL)
        .build()
        .create(RestApiClient::class.java)

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val builder = Retrofit.Builder()
        .client(client)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))

    companion object {
        private const val BASE_URL =
            "https://services1.arcgi.com/0MSEUqKaxRlEPj5g/arcgis/rest/services/ncov_cases/FeatureServer/2/query/"
    }
}