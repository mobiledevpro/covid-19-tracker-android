package com.mobiledevpro.remote.service.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class ApiStatisticsRequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader(
                    name = REFERER_NAME,
                    value = REFERER_VALUE
                )
                .removeHeader(USER_AGENT_NAME)
                .addHeader(
                    name = USER_AGENT_NAME,
                    value = USER_AGENT_VALUE
                )
                .build()
        )
    }

    private companion object {
        const val USER_AGENT_NAME = "User-Agent"
        const val USER_AGENT_VALUE = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36"

        const val REFERER_NAME = "Referer"
        const val REFERER_VALUE = "https://www.arcgis.com/apps/opsdashboard/index.html"
    }
}
