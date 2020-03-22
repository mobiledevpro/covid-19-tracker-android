package com.mobiledevpro.domain.extension

import com.mobiledevpro.domain.model.AccessDeniedThrowable
import com.mobiledevpro.domain.model.ErrorView
import com.mobiledevpro.domain.model.NetworkThrowable
import com.mobiledevpro.domain.model.NotFoundThrowable
import com.mobiledevpro.domain.model.ServiceUnavailableThrowable
import com.mobiledevpro.domain.model.UnknownThrowable
import io.reactivex.exceptions.CompositeException

fun Throwable.toView() = when (this) {

    is NetworkThrowable -> ErrorView.Network(
        message = "Network Error"
    )
    is NotFoundThrowable -> ErrorView.NotFound(
        message = "Not Found Error"
    )
    is AccessDeniedThrowable -> ErrorView.AccessDenied(
        message = "Access Denied Error"
    )
    is ServiceUnavailableThrowable -> ErrorView.ServiceUnavailable(
        message = "Service Unavailable Error"
    )
    is UnknownThrowable -> ErrorView.Unknown(
        message = "Unknown Error"
    )
    else -> ErrorView.Unknown(
        message = "Unknown Error"
    )
}