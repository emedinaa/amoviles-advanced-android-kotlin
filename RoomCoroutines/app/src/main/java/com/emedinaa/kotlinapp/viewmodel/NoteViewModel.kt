package com.emedinaa.kotlinapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.emedinaa.kotlinapp.model.RNoteEntity
import com.emedinaa.kotlinapp.storage.CNoteRepository
import kotlinx.coroutines.launch

class NoteViewModel( val repository:CNoteRepository,application: Application):AndroidViewModel(application) {

    val notes: LiveData<List<RNoteEntity>> = repository.getAllNotes()

    fun add(note:RNoteEntity)= viewModelScope.launch {
        repository.add(note)
    }

    fun update(note:RNoteEntity) = viewModelScope.launch {
        repository.update(note)
    }

    fun delete(note:RNoteEntity) = viewModelScope.launch {
        repository.delete(note)
    }
}