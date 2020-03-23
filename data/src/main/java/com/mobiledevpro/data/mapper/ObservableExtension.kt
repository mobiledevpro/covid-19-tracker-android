package com.mobiledevpro.data.mapper

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

fun <T> Observable<T>.throwableToDomain(): Observable<T> = this
    .onErrorReturn { throw it.throwableToDomain() }

fun <T> Single<T>.throwableToDomain(): Single<T> = this
    .onErrorReturn { throw it.throwableToDomain() }

fun Completable.throwableToDomain(): Completable = this
    .onErrorComplete { throw it.throwableToDomain() }