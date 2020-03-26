package com.mobiledevpro.domain.common


sealed class Result<T> {

    data class Success<T>(val data: T) : Result<T>()

    data class Failure<T>(val error: Error) : Result<T>()
}

enum class Error {
    NETWORK_ERROR,
    NOT_FOUND_ERROR,
    ACCESS_DENIED_ERROR,
    SERVICE_UNAVAILABLE_ERROR,
    HTML_PARSER_ERROR,
    UNKNOWN_ERROR
}

class None

