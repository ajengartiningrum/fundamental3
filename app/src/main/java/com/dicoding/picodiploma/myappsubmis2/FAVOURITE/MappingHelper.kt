package com.dicoding.picodiploma.myappsubmis2.FAVOURITE

import android.database.Cursor
import android.provider.BaseColumns._ID
import java.util.ArrayList

object MappingHelper {
    fun mapCursorToArrayList(favCursor: Cursor?): ArrayList<User> {
        val uList = ArrayList<User>()

        favCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(_ID))
                val avatar = getString(getColumnIndexOrThrow(UserFavoriteColumn.COLUMN_AVATAR_URL))
                val username = getString(getColumnIndexOrThrow(UserFavoriteColumn.COLUMN_USERNAME))
                val name = getString(getColumnIndexOrThrow(UserFavoriteColumn.COLUMN_NAME))
                val location = getString(getColumnIndexOrThrow(UserFavoriteColumn.COLUMN_LOCATION))
                val company = getString(getColumnIndexOrThrow(UserFavoriteColumn.COLUMN_COMPANY))
                val follower = getString(getColumnIndexOrThrow(UserFavoriteColumn.COLUMN_FOLLOWERS))
                val following = getString(getColumnIndexOrThrow(UserFavoriteColumn.COLUMN_FOLLOWING))
                uList.add(User( id, avatar, username, name, location, company, follower, following))
            }
        }
        return uList
    }
}