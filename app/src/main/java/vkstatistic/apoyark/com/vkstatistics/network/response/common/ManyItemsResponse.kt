package vkstatistic.apoyark.com.vkstatistics.network.response.common

data class ManyItemsResponse<out T>(
        val count: Int,
        val items: List<T>
)