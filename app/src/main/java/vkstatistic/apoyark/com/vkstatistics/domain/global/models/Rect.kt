package vkstatistic.apoyark.com.vkstatistics.domain.global.models

import android.os.Parcel
import android.os.Parcelable

data class Rect(
        val x: Double,
        val y: Double,
        val x2: Double,
        val y2: Double

) : Parcelable {
    constructor(parcel: Parcel) : this(
            x = parcel.readDouble(),
            y = parcel.readDouble(),
            x2 = parcel.readDouble(),
            y2 = parcel.readDouble())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(x)
        parcel.writeDouble(y)
        parcel.writeDouble(x2)
        parcel.writeDouble(y2)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Rect> {
        override fun createFromParcel(parcel: Parcel): Rect {
            return Rect(parcel)
        }

        override fun newArray(size: Int): Array<Rect?> {
            return arrayOfNulls(size)
        }
    }
}