package com.mobiledevpro.remote.model.response

import com.google.gson.annotations.SerializedName

data class TotalResponse(
    @SerializedName("features") val feature: List<TotalAttributeResponse>
)

data class TotalAttributeResponse(
    @SerializedName("attributes") val attribute: ValueResponse
)

data class ValueResponse(
    @SerializedName("value") val count: Int
)