package com.mobiledevpro.remote.service.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class ApiRequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val builder = chain.request().newBuilder().apply {
            // There you can add Headers
        }

        return chain.proceed(builder.build())
    }
}