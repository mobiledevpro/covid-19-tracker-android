package com.mobiledevpro.domain.model

/**
 * Model for total values
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 *
 */
data class Total(
        var confirmed: Int = 0,
        var deaths: Int = 0,
        var recovered: Int = 0,
        var updateTime: Long = 0
)