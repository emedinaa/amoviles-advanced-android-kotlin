package com.emedinaa.kotlinapp.storage

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.OnConflictStrategy.IGNORE
import com.emedinaa.kotlinapp.RNoteEntity

@Dao
interface RNoteDao {

    @Query("SELECT * from tb_notes")
    fun notes(): List<RNoteEntity>

    @Query("select * from tb_notes where id = :id")
    fun noteById(id: Int): RNoteEntity

    @Insert(onConflict = REPLACE)
    fun addNote(note: RNoteEntity)

    @Insert(onConflict = IGNORE)
    fun insertOrReplaceNotes(vararg notes: RNoteEntity)

    @Update(onConflict = REPLACE)
    fun updateNote(note: RNoteEntity)

    @Delete
    fun deleteNote(note: RNoteEntity)

    @Query("DELETE from tb_notes")
    fun deleteAll()

}