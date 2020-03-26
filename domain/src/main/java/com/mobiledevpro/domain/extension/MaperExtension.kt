package com.mobiledevpro.domain.extension

import com.mobiledevpro.domain.common.Error
import com.mobiledevpro.domain.common.None
import com.mobiledevpro.domain.common.Result
import com.mobiledevpro.domain.error.AccessDeniedThrowable
import com.mobiledevpro.domain.error.HtmlParserThrowable
import com.mobiledevpro.domain.error.NetworkThrowable
import com.mobiledevpro.domain.error.NotFoundThrowable
import com.mobiledevpro.domain.error.ServiceUnavailableThrowable
import com.mobiledevpro.domain.error.UnknownThrowable
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.exceptions.CompositeException

fun <T> Observable<T>.toResult(): Observable<Result<T>> = this
    .map { Result.Success(it) as Result<T> }
    .onErrorReturn {
        Result.Failure(
            if (it is CompositeException) it.exceptions.last().toView()
            else it.toView()
        )
    }

fun <T> Single<T>.toResult(): Single<Result<T>> = this
    .map { Result.Success(it) as Result<T> }
    .onErrorReturn {
        Result.Failure(
            if (it is CompositeException) it.exceptions.last().toView()
            else it.toView()
        )
    }

fun Completable.toResult(): Single<Result<None>> = this
    .toSingleDefault(Result.Success(None()) as Result<None>)
    .onErrorReturn {
        Result.Failure(
            if (it is CompositeException) it.exceptions.last().toView()
            else it.toView()
        )
    }


private fun Throwable.toView() = when (this) {
    is NetworkThrowable -> Error.NETWORK_ERROR
    is NotFoundThrowable -> Error.NOT_FOUND_ERROR
    is AccessDeniedThrowable -> Error.ACCESS_DENIED_ERROR
    is ServiceUnavailableThrowable -> Error.SERVICE_UNAVAILABLE_ERROR
    is HtmlParserThrowable -> Error.HTML_PARSER_ERROR
    is UnknownThrowable -> Error.UNKNOWN_ERROR
    else -> Error.UNKNOWN_ERROR

}