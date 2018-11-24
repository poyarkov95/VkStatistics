package vkstatistic.apoyark.com.vkstatistics.domain.global.models.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vk.sdk.api.VKError

data class StatisticResponse<out T>(
        @SerializedName("response")
        @Expose
        val response: List<T>? = null,

        @SerializedName("error")
        @Expose
        val error: VKError?
)