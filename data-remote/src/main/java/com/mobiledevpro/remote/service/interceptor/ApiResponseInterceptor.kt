package com.mobiledevpro.remote.service.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class ApiResponseInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val builder = chain.request().newBuilder()

        val response = chain.proceed(builder.build())

        // There you can check response code
        // like response.code() == 432


        //TODO: Need to add this

        /*


    @Override
    public Response intercept(Chain chain) throws IOException {
        try {
            return chain.proceed(chain.request());
        } catch (ConnectException | SocketTimeoutException | UnknownHostException e) {
            throw new IOException(
                    appContext.getResources().getString(R.string.message_check_internet_connection)
            );
        }
    }
         */

        return response
    }
}