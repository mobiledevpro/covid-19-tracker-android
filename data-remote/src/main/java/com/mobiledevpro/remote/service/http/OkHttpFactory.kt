package com.mobiledevpro.remote.service.http

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class OkHttpFactory {

    fun buildOkHttpClient(
        interceptors: List<Interceptor>
    ): OkHttpClient {

        val builder = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)

        interceptors.forEach { builder.addInterceptor(it) }

        return builder.build()
    }

    companion object {
        private const val TIMEOUT = 30L
    }
}