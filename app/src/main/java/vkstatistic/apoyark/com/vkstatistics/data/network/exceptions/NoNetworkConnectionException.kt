package vkstatistic.apoyark.com.vkstatistics.data.network.exceptions

import java.lang.RuntimeException

class NoNetworkConnectionException : RuntimeException {

    constructor() : super()

    constructor(message: String) : super(message)

    constructor(message: String, throwable: Throwable) : super(message, throwable)
}