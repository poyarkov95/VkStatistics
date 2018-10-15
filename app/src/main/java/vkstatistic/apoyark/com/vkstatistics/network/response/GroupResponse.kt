package vkstatistic.apoyark.com.vkstatistics.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import vkstatistic.apoyark.com.vkstatistics.network.model.Group

data class GroupResponse(
        @SerializedName("response")
        @Expose
        val response: Response<Group>
)
