package vkstatistic.apoyark.com.vkstatistics.data.network.exceptions

import java.lang.RuntimeException

class NoNetworkConnectionException : RuntimeException {

    constructor() : super()

    constructor(message: String) : super()

    constructor(message: String, throwable: Throwable) : super(message)
}