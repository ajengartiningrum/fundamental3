package com.dicoding.picodiploma.myappsubmis2.DETAIL

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailItems(
        var avatar: String?,
        var name: String?,
        var username: String?,
        var company: String?,
        var location: String?
): Parcelable