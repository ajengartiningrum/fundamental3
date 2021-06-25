package com.dicoding.picodiploma.myappsubmis2.FAVOURITE

import android.database.Cursor
import android.provider.BaseColumns._ID
import com.dicoding.picodiploma.myappsubmis2.DATABASE.DatabaseContract
import java.util.ArrayList

object MappingHelper {
    fun mapCursorToArrayList(favCursor: Cursor?): ArrayList<Favorite> {
        val uList = ArrayList<Favorite>()

        favCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(_ID))
                val avatar = getString(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.AVATAR))
                val username = getString(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.USERNAME))
                val name = getString(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.NAME))
                val location = getString(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.LOCATION))
                val company = getString(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.COMPANY))
                uList.add(Favorite( id, avatar, username, name, location, company ))
            }
        }
        return uList
    }
}