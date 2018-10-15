package vkstatistic.apoyark.com.vkstatistics.network.response

data class ResponseR<T>(
        val count: Int,
        val items: List<T>
)