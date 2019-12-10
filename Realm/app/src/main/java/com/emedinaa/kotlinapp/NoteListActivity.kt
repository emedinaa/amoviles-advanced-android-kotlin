package com.emedinaa.kotlinapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.emedinaa.kotlinapp.storage.NoteRealmDatabase
import com.emedinaa.kotlinapp.storage.NoteRealmRepository

import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity() {

    private lateinit var noteRepository: NoteRealmRepository
    private lateinit var notes:List<NoteRealm>

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
                val note:NoteRealm= notes[position]
                goToNote(note)
            }
        }
    }

    private fun setupRepository(){
        noteRepository= NoteRealmRepository(NoteRealmDatabase())
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

    private fun goToNote(note:NoteRealm){
        val bundle= Bundle()
        bundle.putInt("NOTE_ID",note.id)
        bundle.putString("NOTE_NAME",note.name)
        bundle.putString("NOTE_DESC",note.description)

        val intent= Intent(this, EditNoteActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

}
