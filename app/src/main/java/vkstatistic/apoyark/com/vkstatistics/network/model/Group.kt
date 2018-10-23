package vkstatistic.apoyark.com.vkstatistics.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Group(
        @SerializedName("id")
        @Expose
        val id: Int,
        @SerializedName("name")
        @Expose
        val name: String,
        @SerializedName("screenName")
        @Expose
        val screen_name: String,
        @SerializedName("isClosed")
        @Expose
        val is_closed: Int,
        @SerializedName("type")
        @Expose
        val type: String,
        @SerializedName("isAdmin")
        @Expose
        val is_admin: Int,
        @SerializedName("isMebmer")
        @Expose
        val is_member: Int,
        @SerializedName("photo_50")
        @Expose
        val photo_50: String,
        @SerializedName("photo_100")
        @Expose
        val photo_100: String,
        @SerializedName("photo_200")
        @Expose
        val photo_200: String,

        var membersCount: String
)