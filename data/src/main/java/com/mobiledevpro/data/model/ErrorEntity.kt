package com.mobiledevpro.data.model

class NetworkThrowableEntity : Throwable()

class NotFoundThrowableEntity : Throwable()

class AccessDeniedThrowableEntity : Throwable()

class ServiceUnavailableThrowableEntity : Throwable()

class UnknownThrowableEntity : Throwable()

class HtmlParserThrowableEntity(override val message: String?) : Throwable()
