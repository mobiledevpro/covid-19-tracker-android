package com.mobiledevpro.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Request / Response for total data
 *
 * Created by Dmitriy Chernysh on Mar 14, 2020.
 *
 * http://androiddev.pro
 *
 */
public class ApiGetTotalDataNetwork {

    public companion object {
        private const val PARAM_RESPONSE_FORMAT = "json"
        const val PARAM_REQUEST_CONFIRMED = 1
        const val PARAM_REQUEST_DEATHS = 2
        const val PARAM_REQUEST_RECOVERED = 3
    }

    private fun RestApiVehicleModels() {}

    public class Request {
        var outStatisticsId = 0

        val format = PARAM_RESPONSE_FORMAT
        val where = "Confirmed>0"
        val geometry: Boolean = false
        val spatialRel: String = "esriSpatialRelIntersects"
        val outFields: String = "*"
        val outSR: Int = 102100
        val cacheHint: Boolean = true
        val outStatistics: String =
                when (outStatisticsId) {
                    PARAM_REQUEST_CONFIRMED -> "[{\"statisticType\":\"sum\",\"onStatisticField\":\"Confirmed\",\"outStatisticFieldName\":\"value\"}]"
                    PARAM_REQUEST_DEATHS -> "[{\"statisticType\":\"sum\",\"onStatisticField\":\"Deaths\",\"outStatisticFieldName\":\"value\"}]"
                    PARAM_REQUEST_RECOVERED -> "[{\"statisticType\":\"sum\",\"onStatisticField\":\"Recovered\",\"outStatisticFieldName\":\"value\"}]"
                    else -> ""
                }


    }

    class Response {
        @SerializedName("features")
        var features: ArrayList<Attributes?>? = null

        fun getValue() =
                features?.get(0)?.value ?: -1


        class Attributes {
            @SerializedName("value")
            var value: Int = 0
        }

    }
}