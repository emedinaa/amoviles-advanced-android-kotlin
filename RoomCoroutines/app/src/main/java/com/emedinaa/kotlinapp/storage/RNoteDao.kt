package com.emedinaa.kotlinapp.storage

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.OnConflictStrategy.IGNORE
import com.emedinaa.kotlinapp.model.RNoteEntity

@Dao
interface RNoteDao {

    @Query("SELECT * from tb_notes")
    fun notesLD(): LiveData<List<RNoteEntity>>

    @Query("select * from tb_notes where id = :id")
    suspend fun noteById(id: Int): RNoteEntity

    @Insert(onConflict = REPLACE)
    suspend fun addNote(note: RNoteEntity)

    @Insert(onConflict = IGNORE)
    suspend fun insertOrReplaceNotes(vararg notes: RNoteEntity)

    @Update(onConflict = REPLACE)
    suspend fun updateNote(note: RNoteEntity)

    @Delete
    suspend fun deleteNote(note: RNoteEntity)

    @Query("DELETE from tb_notes")
    suspend fun deleteAll()

}