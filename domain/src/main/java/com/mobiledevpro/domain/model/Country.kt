package com.mobiledevpro.domain.model

/**
 * Model for Countries list
 *
 * Created by Dmitriy Chernysh on Mar 15, 2020.
 *
 * http://androiddev.pro
 *
 */
data class Country(
    var id: Int = 0, //API:features.attributes.OBJECTID
    var name: String = "", //API: features.attributes.Country_Region
    var confirmed: Int = 0,  //API: features.attributes.Confirmed
    var deaths: Int = 0,  //API: features.attributes.Deaths
    var recovered: Int = 0,  //API: features.attributes.Recovered
    var updateTime: Long = 0,  //API: features.attributes.Last_Update
    var lat: Double = 0.0,  //API: features.attributes.Lat
    var long: Double = 0.0  //API: features.attributes.Long_
)
