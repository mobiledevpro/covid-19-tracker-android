package com.mobiledevpro.app.utils.provider

import android.content.res.Resources
import com.mobiledevpro.app.R
import com.mobiledevpro.domain.common.Error
import com.mobiledevpro.domain.common.Error.ACCESS_DENIED_ERROR
import com.mobiledevpro.domain.common.Error.HTML_PARSER_ERROR
import com.mobiledevpro.domain.common.Error.NETWORK_ERROR
import com.mobiledevpro.domain.common.Error.NOT_FOUND_ERROR
import com.mobiledevpro.domain.common.Error.SERVICE_UNAVAILABLE_ERROR
import com.mobiledevpro.domain.common.Error.UNKNOWN_ERROR

class DefaultResourceProvider(
    private val resources: Resources
) : ResourceProvider {

    override fun getErrorMessage(error: Error): String = resources.getString(
        when (error.name) {
            NETWORK_ERROR.name -> R.string.network_error
            NOT_FOUND_ERROR.name -> R.string.not_found_error
            ACCESS_DENIED_ERROR.name -> R.string.access_denied_error
            SERVICE_UNAVAILABLE_ERROR.name -> R.string.service_unavailable_error
            HTML_PARSER_ERROR.name -> R.string.parsing_error
            UNKNOWN_ERROR.name -> R.string.unknown_error
            else -> throw IllegalArgumentException("Illegal parameter!")
        }
    )
}
