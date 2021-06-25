package com.dicoding.picodiploma.myappsubmis2.DATABASE

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns._ID
import com.dicoding.picodiploma.myappsubmis2.DATABASE.DatabaseContract.FavoriteColumns.Companion.AVATAR
import com.dicoding.picodiploma.myappsubmis2.DATABASE.DatabaseContract.FavoriteColumns.Companion.COMPANY
import com.dicoding.picodiploma.myappsubmis2.DATABASE.DatabaseContract.FavoriteColumns.Companion.LOCATION
import com.dicoding.picodiploma.myappsubmis2.DATABASE.DatabaseContract.FavoriteColumns.Companion.TABLE_NAME
import com.dicoding.picodiploma.myappsubmis2.DATABASE.DatabaseContract.FavoriteColumns.Companion.USERNAME

internal class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_NAME = "dbuseritems"
        private const val DATABASE_VERSION = 1

        private const val SQL_CREATE_TABLE_ITEMS = "CREATE TABLE $TABLE_NAME" +
                "(${_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                " ${USERNAME} TEXT NOT NULL," +
                " ${AVATAR} TEXT NOT NULL," +
                " ${LOCATION} TEXT NOT NULL," +
                " ${COMPANY} TEXT NOT NULL,"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE_ITEMS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}