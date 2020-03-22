package com.mobiledevpro.domain.model

sealed class Result<T> {

    data class Success<T>(val data: T) : Result<T>()

    data class Failure<T>(val error: ErrorView) : Result<T>()
}

class None

