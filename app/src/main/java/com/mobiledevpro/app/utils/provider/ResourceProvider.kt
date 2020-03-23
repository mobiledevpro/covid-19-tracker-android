package com.mobiledevpro.app.utils.provider

import com.mobiledevpro.domain.common.Error

interface ResourceProvider {
    fun getErrorMessage(error: Error): String
}