package com.mobiledevpro.remote.model.response

import com.google.gson.annotations.SerializedName

data class CountiesStatisticResponse(
    @SerializedName("features") val countries: List<CountriesStatisticAttributeResponse>
)

data class CountriesStatisticAttributeResponse(
    @SerializedName("attributes") val attribute: CountryStatisticResponse
)

data class CountryStatisticResponse(
    @SerializedName("OBJECTID") val id: Int,
    @SerializedName("Country_Region") val country: String,
    @SerializedName("Last_Update") val updated: Long,
    @SerializedName("Confirmed") val confirmed: Long,
    @SerializedName("Deaths") val deaths: Long,
    @SerializedName("Delta_Confirmed") val deltaConfirmed: Long
)
