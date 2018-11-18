package vkstatistic.apoyark.com.vkstatistics.domain.global.models.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.error.Error

data class StatisticResponse<out T>(
        @SerializedName("response")
        @Expose
        val response: List<T>,

        @SerializedName("error")
        @Expose
        val error: Error
)