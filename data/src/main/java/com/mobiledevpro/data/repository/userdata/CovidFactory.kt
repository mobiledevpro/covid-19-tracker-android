package com.mobiledevpro.data.repository.userdata

class CovidFactory(
    private val covidCache: CovidCache,
    private val covidRemote: CovidRemote
) {

    fun remote() = covidRemote

    fun cache() = covidCache
}