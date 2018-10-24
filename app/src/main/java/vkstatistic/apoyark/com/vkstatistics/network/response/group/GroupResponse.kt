package vkstatistic.apoyark.com.vkstatistics.network.response.group

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import vkstatistic.apoyark.com.vkstatistics.network.response.searchgroup.SingleItemResponse

data class GroupResponse<out T>(
        @SerializedName("response")
        @Expose
        val response: SingleItemResponse<T>?
)