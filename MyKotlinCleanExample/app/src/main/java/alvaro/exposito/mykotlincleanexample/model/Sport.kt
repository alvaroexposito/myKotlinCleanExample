package alvaro.exposito.mykotlincleanexample.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by alvaroexpositoguerra on 13/5/17.
 */
data class Sport(val imageUrl: String, val id: Int) : Parcelable {

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<Sport> {
            override fun createFromParcel(parcelIn: Parcel): Sport {
                return Sport(parcelIn.readString(), parcelIn.readInt())
            }

            override fun newArray(size: Int): Array<Sport?> {
                return arrayOfNulls(size)
            }
        }
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(imageUrl)
        p0?.writeInt(id)
    }

    override fun describeContents(): Int = 0
}