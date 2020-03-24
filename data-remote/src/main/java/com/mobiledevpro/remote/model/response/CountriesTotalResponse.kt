package com.mobiledevpro.remote.model.response

import com.google.gson.annotations.SerializedName

data class CountriesTotalResponse(
    @SerializedName("features") val countries: List<CountriesTotalAttributeResponse>
)

data class CountriesTotalAttributeResponse(
    @SerializedName("attributes") val attribute: CountryTotalResponse
)

data class CountryTotalResponse(
    @SerializedName("OBJECTID") val id: Int,
    @SerializedName("Country_Region") val country: String,
    @SerializedName("Last_Update") val updated: Long,
    @SerializedName("Lat") val latitude: Double,
    @SerializedName("Long_") val longitude: Double,
    @SerializedName("Confirmed") val confirmed: Int,
    @SerializedName("Deaths") val deaths: Int,
    @SerializedName("Recovered") val recovered: Int,
    @SerializedName("Active") val active: Int
)