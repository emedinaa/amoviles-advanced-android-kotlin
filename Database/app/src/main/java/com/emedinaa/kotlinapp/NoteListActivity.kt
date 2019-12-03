package com.emedinaa.kotlinapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.emedinaa.kotlinapp.storage.NoteDatabase
import com.emedinaa.kotlinapp.storage.NoteRepository

import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity() {

    private lateinit var noteRepository: NoteRepository
    private lateinit var notes:List<Note>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setupRepository()
        ui()

        //loadNotes()
    }

    private fun ui(){
        btnAddNote.setOnClickListener {
            goToAddNote()
        }

        lstNotes.setOnItemClickListener { _, _, position, _ ->
            if(notes.isNotEmpty()){
                val note:Note= notes[position]
                goToNote(note)
            }
        }
    }

    private fun setupRepository(){
        noteRepository= NoteRepository(NoteDatabase(this))
    }

    override fun onResume() {
        super.onResume()
        loadNotes()
    }

    private fun loadNotes(){
        notes= noteRepository.notes()
        lstNotes.adapter= NoteAdapter(this,notes)
    }


    private fun goToAddNote(){
        startActivity(Intent(this, AddNoteActivity::class.java))
    }

    private fun goToNote(note:Note){
        val bundle= Bundle()
        bundle.putSerializable("NOTE",note)
        val intent= Intent(this, EditNoteActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

}
