package com.emedinaa.kotlinapp.storage

import android.content.Context
import androidx.lifecycle.LiveData
import com.emedinaa.kotlinapp.model.RNoteEntity

class CNoteRepository(context:Context) {

    private lateinit var noteDao:RNoteDao

    init{
        val db = RNoteDataBase.getInstance(context)
        db?.let {
            noteDao = it.noteDao()
        }
    }

    fun getAllNotes():LiveData<List<RNoteEntity>>{
        return noteDao.notesLD()
    }

    suspend fun add(noteEntity: RNoteEntity) {
        noteDao.addNote(noteEntity)
    }

    suspend fun update(noteEntity: RNoteEntity) {
        noteDao.updateNote(noteEntity)
    }

    suspend fun delete(noteEntity: RNoteEntity) {
        noteDao.deleteNote(noteEntity)
    }

}