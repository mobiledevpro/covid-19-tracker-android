package com.mobiledevpro.remote

import android.content.Context
import com.mobiledevpro.remote.interceptor.HandleHttpErrorsInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * RestAPI client with Retrofit
 *
 *
 * Created by Dmitriy V. Chernysh on 11/2/18.
 *
 *
 * https://instagr.am/mobiledevpro
 * https://github.com/dmitriy-chernysh
 * #MobileDevPro
 */
class RestApiClient(private val context: Context) {
    private var apiInterface: IRestApiClient

    companion object {
        const val BASE_URL = "https://services1.arcgis.com/0MSEUqKaxRlEPj5g/arcgis/rest/services/ncov_cases/FeatureServer/1/query/"
        private const val HTTP_TIMEOUT = 30 //in seconds
    }

    init {
        val httpClient = OkHttpClient.Builder()

        httpClient
                .readTimeout(HTTP_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .connectTimeout(HTTP_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)

        httpClient.interceptors().add(HandleHttpErrorsInterceptor(context))

        /*
        //for logging -->
        val logging = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message: String ->
            //log all requests to Crashlytics
            // Split by line, then ensure each line can fit into Log's maximum length.
            if (BuildConfig.DEBUG) {
                var splitStr = ""
                var i = 0
                val length = message.length
                while (i < length) {
                    var newline = message.indexOf('\n', i)
                    newline = if (newline != -1) newline else length
                    do {
                        val end = Math.min(newline, i + 4000)
                        splitStr = message.substring(i, end)
                        Log.d("OkHttp", splitStr)
                        i = end
                    } while (i < newline)
                    i++
                }
            }
        })

        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        // add logging as last interceptor
        httpClient.addInterceptor(logging) // <-- this is the important line!
        //<!-- for loggining

         */
        val builder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
        apiInterface = builder.build().create(IRestApiClient::class.java)
    }

    /**
     * Method for checking network connection
     *
     * @param context - application context
     * @return true - device online
     */
    // @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    /*   fun isDeviceOnline(context: Context): Boolean {
           val connMngr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                   ?: return false
           val netInfo = connMngr.activeNetworkInfo
           return netInfo != null && netInfo.isConnected
       }

       fun isDeviceOnlineRx(context: Context): Single<Boolean?> {
           return Single.create { emitter: SingleEmitter<Boolean?> ->
               if (emitter.isDisposed) return@create
               emitter.onSuccess(
                       isDeviceOnline(context)
               )
           }
       }

     */
}