package com.emedinaa.kotlinapp.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class BNoteDatabase(context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null,DATABASE_VERSION)  {

    companion object {
        const val DATABASE_VERSION:Int=1
        const val DATABASE_NAME:String="BDBasicNote"

        //note table
        const val BNOTE_TABLE_NAME="tb_notes"
        const val BNOTE_KEY_ID:String= "id"
        const val BNOTE_KEY_NAME:String= "name"
        const val BNOTE_KEY_DESC:String= "desc"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = ("CREATE TABLE ${BNOTE_TABLE_NAME} ("
                + "${BNOTE_KEY_ID} INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , ${BNOTE_KEY_NAME}  TEXT,"
                + "${BNOTE_KEY_DESC} TEXT )")

        Log.v("CONSOLE","sql $sql")
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val sql = "DROP TABLE IF EXISTS ${BNOTE_TABLE_NAME}"
        db?.execSQL(sql)
    }
}