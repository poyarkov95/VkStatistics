package vkstatistic.apoyark.com.vkstatistics.domain.global.models

import android.os.Parcel
import android.os.Parcelable

data class Photo(
        val id: Int,
        val album_id: Int,
        val owner_id: Int,
        val user_id: Int,
        val sizes: List<Size>,
        val text: String,
        val date: Int,
        val post_id: Int

) : Parcelable {
    constructor(parcel: Parcel) : this(
            id = parcel.readInt(),
            album_id = parcel.readInt(),
            owner_id = parcel.readInt(),
            user_id = parcel.readInt(),
            sizes = parcel.createTypedArrayList(Size),
            text = parcel.readString(),
            date = parcel.readInt(),
            post_id = parcel.readInt())


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(album_id)
        parcel.writeInt(owner_id)
        parcel.writeInt(user_id)
        parcel.writeTypedList(sizes)
        parcel.writeString(text)
        parcel.writeInt(date)
        parcel.writeInt(post_id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Photo> {
        override fun createFromParcel(parcel: Parcel): Photo {
            return Photo(parcel)
        }

        override fun newArray(size: Int): Array<Photo?> {
            return arrayOfNulls(size)
        }
    }
}