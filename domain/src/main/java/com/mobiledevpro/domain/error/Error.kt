package com.mobiledevpro.domain.error

class NetworkThrowable : Throwable()

class NotFoundThrowable : Throwable()

class AccessDeniedThrowable : Throwable()

class ServiceUnavailableThrowable : Throwable()

class HtmlParserThrowable(override val message: String?) : Throwable()

class UnknownThrowable : Throwable()
