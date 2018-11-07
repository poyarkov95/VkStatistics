package vkstatistic.apoyark.com.vkstatistics.domain.global.models.group

import android.os.Parcel
import android.os.Parcelable
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

) : Parcelable { // to be able to put it inside intent extras. Parcelable works faster than Serializable
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(CropPhoto::class.java.classLoader),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(screen_name)
        parcel.writeInt(is_closed)
        parcel.writeString(type)
        parcel.writeInt(is_admin)
        parcel.writeInt(is_memberis_member)
        parcel.writeString(photo_50)
        parcel.writeString(photo_100)
        parcel.writeString(photo_200)
        parcel.writeString(description)
        parcel.writeString(activity)
        parcel.writeString(start_date)
        parcel.writeParcelable(cropPhoto, flags)
        parcel.writeString(members_count)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Group> {
        override fun createFromParcel(parcel: Parcel): Group {
            return Group(parcel)
        }

        override fun newArray(size: Int): Array<Group?> {
            return arrayOfNulls(size)
        }
    }

    fun getBigSisedGroupImageUrl(): String {
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