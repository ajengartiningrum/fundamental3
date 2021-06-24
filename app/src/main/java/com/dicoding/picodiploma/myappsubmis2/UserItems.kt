package com.dicoding.picodiploma.myappsubmis2

import android.os.Parcel
import android.os.Parcelable


class UserItems(
    var avatar: String?,
    var username: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(avatar)
        parcel.writeString(username)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserItems> {
        override fun createFromParcel(parcel: Parcel): UserItems {
            return UserItems(parcel)
        }

        override fun newArray(size: Int): Array<UserItems?> {
            return arrayOfNulls(size)
        }
    }
}