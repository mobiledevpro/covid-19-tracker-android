package com.mobiledevpro.remote.model.response

import com.google.gson.annotations.SerializedName

data class CountriesResponse(
    @SerializedName("features") val countries: List<CountriesAttributeResponse>
)

data class CountriesAttributeResponse(
    @SerializedName("attributes") val attribute: CountryResponse
)

data class CountryResponse(
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