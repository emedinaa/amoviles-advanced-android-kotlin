package com.emedinaa.kotlinapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.emedinaa.kotlinapp.adapter.NoteAdapter
import com.emedinaa.kotlinapp.decorator.SpacesItemDecorator
import com.emedinaa.kotlinapp.model.Note
import com.emedinaa.kotlinapp.storage.NoteDatabase
import com.emedinaa.kotlinapp.storage.NoteRepository

import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity() {

    private lateinit var noteRepository: NoteRepository
    private var notes:List<Note> = emptyList()
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setupRepository()
        ui()
    }

    private fun ui(){
        noteAdapter= NoteAdapter(notes, selectedItem())

        //val dividerV = DividerItemDecoration(this,DividerItemDecoration.VERTICAL)
        //val dividerH = DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL)
        val spacesItem = SpacesItemDecorator(20)
        recyclerViewNotes.apply {
            layoutManager = GridLayoutManager(this@NoteListActivity,2)
            addItemDecoration(spacesItem)
            //addItemDecoration(dividerV)
            //addItemDecoration(dividerH)
            adapter= noteAdapter
        }

        fabAdd.setOnClickListener {
            goToAddNote()
        }
    }

    private fun setupRepository(){
        noteRepository= NoteRepository(NoteDatabase(this))
    }

    private fun selectedItem():(note: Note)->Unit{
        return{
            goToNote(it)
        }
    }

    override fun onResume() {
        super.onResume()
        loadNotes()
    }

    private fun loadNotes(){
        notes= noteRepository.notes()
        noteAdapter.update(notes)
    }

    private fun goToAddNote(){
        startActivity(Intent(this, AddNoteActivity::class.java))
    }

    private fun goToNote(note: Note){
        val bundle= Bundle()
        bundle.putSerializable("NOTE",note)
        val intent= Intent(this, EditNoteActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

}
