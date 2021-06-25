package com.dicoding.picodiploma.myappsubmis2.FAVOURITE

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns._ID
import com.dicoding.picodiploma.myappsubmis2.DATABASE.DatabaseContract.FavoriteColumns.Companion.TABLE_NAME
import com.dicoding.picodiploma.myappsubmis2.DATABASE.DatabaseContract.FavoriteColumns.Companion.USERNAME
import com.dicoding.picodiploma.myappsubmis2.DATABASE.DatabaseHelper
import java.sql.SQLException

class FavoriteHelper(context: Context) {

    private var databaseHelper: DatabaseHelper =
        DatabaseHelper(context)
    private lateinit var database: SQLiteDatabase

    companion object{
        private const val DATABASE_TABLE =  TABLE_NAME
        private var INSTANCE: FavoriteHelper? = null

        fun getInstance(context: Context): FavoriteHelper =
            INSTANCE
                    ?: synchronized(this){
                INSTANCE
                        ?: FavoriteHelper(context)
            }
    }

    @Throws(SQLException::class)
    fun open(){
        database = databaseHelper.writableDatabase
    }

    fun close(){
        databaseHelper.close()

        if (database.isOpen)
            database.close()
    }

    fun queryAll(): Cursor {
        return database.query(
                DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$_ID ASC"
        )
    }

    fun update(id: String, values: ContentValues?): Int {
        return database.update(DATABASE_TABLE, values, "$USERNAME = ?", arrayOf(id))
    }

    fun queryById(id: String): Cursor {
        return database.query(
                DATABASE_TABLE,
            null,
            "$_ID = ?",
            arrayOf(id),
            null,
            null,
            null,
            null
        )
    }

    fun insert(values: ContentValues?): Long{
        return database.insert(DATABASE_TABLE, null, values)
    }

    fun deleteById(id: String): Int{
        return database.delete(DATABASE_TABLE, "$_ID = '$id'", null)
    }

}