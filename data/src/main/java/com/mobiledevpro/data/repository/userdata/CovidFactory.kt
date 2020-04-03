package com.mobiledevpro.data.repository.userdata

class CovidFactory(
    private val totalCovidCache: TotalCovidCache,
    private val totalCovidRemote: TotalCovidRemote
) {

    fun remote() = totalCovidRemote

    fun cache() = totalCovidCache
}