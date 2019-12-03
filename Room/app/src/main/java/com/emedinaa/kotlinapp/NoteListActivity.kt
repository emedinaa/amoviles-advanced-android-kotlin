package com.emedinaa.kotlinapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.emedinaa.kotlinapp.storage.RNoteRepository

import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity() {

    private lateinit var noteRepository: RNoteRepository
    private lateinit var notes:List<RNoteEntity>

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
                val note:RNoteEntity= notes[position]
                goToNote(note)
            }
        }
    }

    private fun setupRepository(){
        noteRepository= (application as RNoteApplication).getNoteRepository()
    }

    override fun onResume() {
        super.onResume()
        loadNotes()
    }

    private fun loadNotes(){
        noteRepository.getAllNotes(object :RNoteRepository.PopulateCallback{
            override fun onFailure(e: Exception) {}

            override fun onSuccess(noteEntities: List<RNoteEntity>) {
                notes= noteEntities
                lstNotes.adapter= RNoteAdapter(this@NoteListActivity,notes)
            }
        })
    }

    private fun goToAddNote(){
        startActivity(Intent(this, AddNoteActivity::class.java))
    }

    private fun goToNote(note:RNoteEntity){
        val bundle= Bundle()
        bundle.putSerializable("NOTE",note)
        val intent= Intent(this, EditNoteActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

}
