package vkstatistic.apoyark.com.vkstatistics.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GroupResponse<T>(
        @SerializedName("response")
        @Expose
        val response: Response<T>?
)
