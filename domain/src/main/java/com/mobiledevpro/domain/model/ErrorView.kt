package com.mobiledevpro.domain.model

sealed class ErrorView {

    data class Network(val message: String) : ErrorView()

    data class NotFound(val message: String) : ErrorView()

    data class AccessDenied(val message: String) : ErrorView()

    data class ServiceUnavailable(val message: String) : ErrorView()

    data class Unknown(val message: String) : ErrorView()
}