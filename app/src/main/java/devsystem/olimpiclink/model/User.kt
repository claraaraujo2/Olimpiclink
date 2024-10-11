package devsystem.olimpiclink.model

import android.os.Parcel
import android.os.Parcelable

data class User(
    var id_user : Int,
    var name_user : String,
    var email_user : String,
    var login_user : String,
    var url_profile_picture_user : String?,
    var created_at_user : String,
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id_user)
        parcel.writeString(name_user)
        parcel.writeString(email_user)
        parcel.writeString(login_user)
        parcel.writeString(url_profile_picture_user)
        parcel.writeString(created_at_user)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

}
