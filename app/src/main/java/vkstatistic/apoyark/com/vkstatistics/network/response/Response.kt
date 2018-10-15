package vkstatistic.apoyark.com.vkstatistics.network.response

data class Response<T>(
        val count: Int,
        val items: List<T>
)