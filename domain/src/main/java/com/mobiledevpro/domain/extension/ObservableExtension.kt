package com.mobiledevpro.domain.extension

import com.mobiledevpro.domain.model.None
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import com.mobiledevpro.domain.model.Result

fun <T>Observable<T>.toResult(): Observable<Result<T>> = this
    .map { Result.Success(it) as Result<T> }
    .onErrorReturn { Result.Failure(it.toView()) }

fun <T>Single<T>.toResult(): Single<Result<T>> = this
    .map { Result.Success(it) as Result<T> }
    .onErrorReturn { Result.Failure(it.toView()) }

fun Completable.toResult(): Single<Result<None>> = this
    .toSingleDefault(Result.Success(None()) as Result<None>)
    .onErrorReturn { Result.Failure(it.toView()) }
