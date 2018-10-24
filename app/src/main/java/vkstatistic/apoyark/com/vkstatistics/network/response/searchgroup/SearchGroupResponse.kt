package vkstatistic.apoyark.com.vkstatistics.network.response.searchgroup

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import vkstatistic.apoyark.com.vkstatistics.network.response.common.ManyItemsResponse

data class SearchGroupResponse<out T>(
        @SerializedName("response")
        @Expose
        val response: ManyItemsResponse<T>?
)
