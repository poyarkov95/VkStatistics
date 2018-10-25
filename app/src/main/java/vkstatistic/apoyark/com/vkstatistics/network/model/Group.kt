package vkstatistic.apoyark.com.vkstatistics.network.model

import android.os.Parcel
import android.os.Parcelable
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
        val description: String,
        @SerializedName("activity")
        @Expose
        val activity: String?,
        @SerializedName("start_date")
        @Expose
        val start_date: String?,
        @SerializedName("members_count")
        @Expose
        var members_count: String?

) : Parcelable { // to be able to put it inside intent extras. Parcelable works faster than Serializable
    constructor(parcel: Parcel) : this(
            id = parcel.readInt(),
            name = parcel.readString(),
            screen_name = parcel.readString(),
            is_closed = parcel.readInt(),
            type = parcel.readString(),
            is_admin = parcel.readInt(),
            is_memberis_member = parcel.readInt(),
            photo_50 = parcel.readString(),
            photo_100 = parcel.readString(),
            photo_200 = parcel.readString(),
            description = parcel.readString(),
            activity = parcel.readString(),
            start_date = parcel.readString(),
            members_count = parcel.readString())

    override fun writeToParcel(parselable: Parcel, p1: Int) {
        parselable.writeInt(id)
        parselable.writeString(name)
        parselable.writeString(screen_name)
        parselable.writeInt(is_closed)
        parselable.writeString(type)
        parselable.writeInt(is_admin)
        parselable.writeInt(is_admin)
        parselable.writeString(photo_50)
        parselable.writeString(photo_100)
        parselable.writeString(photo_100)
        parselable.writeString(photo_200)
        parselable.writeString(description)
        parselable.writeString(activity)
        parselable.writeString(start_date)
        parselable.writeString(members_count)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Group> {
        override fun createFromParcel(parcel: Parcel): Group {
            return Group(parcel)
        }

        override fun newArray(size: Int): Array<Group?> {
            return arrayOfNulls(size)
        }
    }
}