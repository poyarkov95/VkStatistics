package vkstatistic.apoyark.com.vkstatistics.domain.global.models.response

data class ManyItemsResponse<out T>(
        val count: Int?,
        val items: List<T>
)