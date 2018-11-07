package vkstatistic.apoyark.com.vkstatistics.domain.global.models.response

data class CountableResponse<out T>(
        val count: Int?,
        val items: List<T>
)