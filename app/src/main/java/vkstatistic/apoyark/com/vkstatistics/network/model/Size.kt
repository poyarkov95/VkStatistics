package vkstatistic.apoyark.com.vkstatistics.network.model

import android.os.Parcel
import android.os.Parcelable

data class Size(
        val type: String,
        val url: String,
        val width: Int,
        val height: Int

) : Parcelable {
    constructor(parcel: Parcel) : this(
            type = parcel.readString(),
            url = parcel.readString(),
            width = parcel.readInt(),
            height = parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(type)
        parcel.writeString(url)
        parcel.writeInt(width)
        parcel.writeInt(height)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Size> {
        override fun createFromParcel(parcel: Parcel): Size {
            return Size(parcel)
        }

        override fun newArray(size: Int): Array<Size?> {
            return arrayOfNulls(size)
        }
    }
}