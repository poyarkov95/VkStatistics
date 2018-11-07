package vkstatistic.apoyark.com.vkstatistics.domain.global.models.group

import android.os.Parcel
import android.os.Parcelable

data class CropPhoto(
        val photo: Photo,
        val crop: Crop,
        val rect: Rect

) : Parcelable {
    constructor(parcel: Parcel) : this(
            photo = parcel.readParcelable(Photo::class.java.classLoader),
            crop = parcel.readParcelable(Crop::class.java.classLoader),
            rect = parcel.readParcelable(Rect::class.java.classLoader))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(photo, flags)
        parcel.writeParcelable(crop, flags)
        parcel.writeParcelable(rect, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CropPhoto> {
        override fun createFromParcel(parcel: Parcel): CropPhoto {
            return CropPhoto(parcel)
        }

        override fun newArray(size: Int): Array<CropPhoto?> {
            return arrayOfNulls(size)
        }
    }
}







