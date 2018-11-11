package vkstatistic.apoyark.com.vkstatistics.domain.global.models.group

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Group( //todo might be there is no need to specify @SerializedName annotation
        @SerializedName("id")
        @Expose
        val id: Int,
        @SerializedName("name")
        @Expose
        val name: String,
        @SerializedName("screenName")
        @Expose
        val screen_name: String?,
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
        val is_memberis_member: Int,
        @SerializedName("photo_50")
        @Expose
        val photo_50: String,
        @SerializedName("photo_100")
        @Expose
        val photo_100: String,
        @SerializedName("photo_200")
        @Expose
        val photo_200: String,
        @SerializedName("description")
        @Expose
        val description: String?,
        @SerializedName("activity")
        @Expose
        val activity: String?,
        @SerializedName("start_date")
        @Expose
        val start_date: String?,
        @SerializedName("crop_photo")
        @Expose
        val cropPhoto: CropPhoto?,
        @SerializedName("members_count")
        @Expose
        var members_count: String?

) {

    fun getBigSizedGroupImageUrl(): String {
        if (cropPhoto != null) {
            val qSizedImage = cropPhoto.photo.sizes.find { size -> size.type == "q" }
            if (qSizedImage != null) {
                return qSizedImage.url
            }
            val rSizedImage = cropPhoto.photo.sizes.find { size -> size.type == "r" }
            if (rSizedImage != null) {
                return rSizedImage.url
            }
        }
        return photo_200
    }
}