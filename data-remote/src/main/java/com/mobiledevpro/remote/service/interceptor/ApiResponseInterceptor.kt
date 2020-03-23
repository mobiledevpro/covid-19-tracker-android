package com.mobiledevpro.remote.service.interceptor

import com.mobiledevpro.data.model.AccessDeniedThrowableEntity
import com.mobiledevpro.data.model.NetworkThrowableEntity
import com.mobiledevpro.data.model.NotFoundThrowableEntity
import com.mobiledevpro.data.model.ServiceUnavailableThrowableEntity
import com.mobiledevpro.data.model.UnknownThrowableEntity
import okhttp3.Interceptor
import okhttp3.Response
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.net.UnknownServiceException


class ApiResponseInterceptor : Interceptor {

    @Throws(Throwable::class)
    override fun intercept(chain: Interceptor.Chain): Response = try {

        val request = chain.request()
        val response = chain.proceed(request)
        when (response.code) {
            HttpURLConnection.HTTP_OK -> response
            HttpURLConnection.HTTP_NOT_FOUND -> throw NotFoundThrowableEntity()
            HttpURLConnection.HTTP_FORBIDDEN -> throw AccessDeniedThrowableEntity()
            HttpURLConnection.HTTP_UNAVAILABLE -> throw ServiceUnavailableThrowableEntity()
            else -> throw UnknownThrowableEntity()
        }
    } catch (e: Throwable) {
        throw when (e){
            is UnknownHostException -> NetworkThrowableEntity()
            is SocketTimeoutException -> NetworkThrowableEntity()
            is ConnectException -> NetworkThrowableEntity()
            is UnknownServiceException -> NotFoundThrowableEntity()
            else -> UnknownThrowableEntity()
        }

    }
}