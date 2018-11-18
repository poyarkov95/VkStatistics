package vkstatistic.apoyark.com.vkstatistics.data.network.interseptors

import okhttp3.Interceptor
import okhttp3.Response
import vkstatistic.apoyark.com.vkstatistics.data.network.NetworkChecker
import vkstatistic.apoyark.com.vkstatistics.data.network.exceptions.NoNetworkConnectionException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NetworkCheckerInterceptor(val networkChecker: NetworkChecker) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        if (!networkChecker.isConnected()) {
            throw NoNetworkConnectionException("No network connection.")
        }
        try {
            return chain.proceed(requestBuilder.build())
        } catch (e: SocketTimeoutException) {
            throw SocketTimeoutException()
        } catch (e: UnknownHostException) {
            throw UnknownHostException()
        }
    }
}