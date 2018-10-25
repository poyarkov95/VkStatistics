package vkstatistic.apoyark.com.vkstatistics.network.response.common

data class SingleItemResponse<out T>(
        val items: List<T>
)