package com.dicoding.picodiploma.myappsubmis2.FAVOURITE

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.dicoding.picodiploma.myappsubmis2.DATABASE.DatabaseContract.AUTHORITY
import com.dicoding.picodiploma.myappsubmis2.DATABASE.DatabaseContract.FavoriteColumns.Companion.CONTENT_URI
import com.dicoding.picodiploma.myappsubmis2.DATABASE.DatabaseContract.FavoriteColumns.Companion.TABLE_NAME

class FavoriteProvider : ContentProvider() {
    companion object {
        private const val FAVORITE = 1
        private const val FAVORITE_ID = 2
        private lateinit var favoriteHelper: FavoriteHelper
        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            sUriMatcher.addURI(AUTHORITY,  TABLE_NAME, FAVORITE)
            sUriMatcher.addURI(AUTHORITY, "$TABLE_NAME/#", FAVORITE_ID)
        }
    }

    override fun onCreate(): Boolean {
        favoriteHelper = FavoriteHelper.getInstance(context as Context)
        favoriteHelper.open()
        return true
    }

    override fun query(
            uri: Uri,
            strings: Array<String>?,
            s: String?,
            strings1: Array<String>?,
            s1: String?
    ): Cursor? {
        return when (sUriMatcher.match(uri)) {
            FAVORITE -> favoriteHelper.queryAll() // get all data
            FAVORITE_ID -> favoriteHelper.queryById(uri.lastPathSegment.toString()) // get data by id
            else -> null
        }
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, contentValues: ContentValues?): Uri? {
        val added: Long = when (FAVORITE) {
            sUriMatcher.match(uri) -> favoriteHelper.insert(contentValues)
            else -> 0
        }

        context?.contentResolver?.notifyChange(CONTENT_URI, null)
        return Uri.parse("$CONTENT_URI/$added")
    }

    override fun update(
            uri: Uri,
            contentValues: ContentValues?,
            s: String?,
            strings: Array<String>?
    ): Int {
        val updated: Int = when (FAVORITE_ID) {
            sUriMatcher.match(uri) -> favoriteHelper.update(
                    uri.lastPathSegment.toString(),
                    contentValues
            )
            else -> 0
        }

        context?.contentResolver?.notifyChange(CONTENT_URI, null)
        return updated
    }

    override fun delete(uri: Uri, s: String?, strings: Array<String>?): Int {
        val deleted: Int = when (FAVORITE_ID) {
            sUriMatcher.match(uri) -> favoriteHelper.deleteById(uri.lastPathSegment.toString())
            else -> 0
        }

        context?.contentResolver?.notifyChange(CONTENT_URI, null)
        return deleted
    }
}