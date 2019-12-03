package com.emedinaa.kotlinapp.db

import android.content.ContentValues
import android.provider.ContactsContract
import com.emedinaa.kotlinapp.db.BNote
import com.emedinaa.kotlinapp.db.BNoteDatabase

class BNoteRepository(val noteDatabase: BNoteDatabase) {

    fun notes():List<BNote>{
        val sqliteDatabase= noteDatabase.readableDatabase //modo lectura

        val notes:MutableList<BNote> = mutableListOf()
        val sql= "SELECT  * FROM ${BNoteDatabase.BNOTE_TABLE_NAME}"
        val cursor= sqliteDatabase.rawQuery(sql,null)
        if(cursor.moveToFirst()){
            do{
                val note= BNote(cursor.getString(0).toInt(),
                        cursor.getString(1),
                        cursor.getString(2))
                notes.add(note)
            }while(cursor.moveToNext())
        }
        sqliteDatabase.close()
        return notes.toList()
    }

    fun addNote(note:BNote){
        val sqliteDatabase= noteDatabase.writableDatabase //modo escritura

        val contentValues= ContentValues()
        contentValues.put(BNoteDatabase.BNOTE_KEY_NAME,note.name)
        contentValues.put(BNoteDatabase.BNOTE_KEY_DESC,note.description)

        sqliteDatabase.insert(BNoteDatabase.BNOTE_TABLE_NAME,null,contentValues)
        sqliteDatabase.close()
    }

    fun deleteNote(note:BNote):Int{
        val sqliteDatabase= noteDatabase.writableDatabase //modo escritura
        val row= sqliteDatabase.delete(BNoteDatabase.BNOTE_TABLE_NAME,
            "${BNoteDatabase.BNOTE_KEY_ID}=?", arrayOf(note.id.toString()))
        sqliteDatabase.close()
        return row
    }

    fun updateNote(note: BNote):Int{
        val sqliteDatabase= noteDatabase.writableDatabase //modo escritura
        val contentValues=ContentValues()
        contentValues.put(BNoteDatabase.BNOTE_KEY_NAME,note.name)
        contentValues.put(BNoteDatabase.BNOTE_KEY_DESC,note.description)

        val row= sqliteDatabase.update(BNoteDatabase.BNOTE_TABLE_NAME,contentValues,
            "${BNoteDatabase.BNOTE_KEY_ID}=?", arrayOf(note.id.toString()))
        sqliteDatabase.close()
        return row
    }
}