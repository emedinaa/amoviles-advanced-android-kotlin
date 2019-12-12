package com.emedinaa.kotlinapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.emedinaa.kotlinapp.di.Injector
import com.emedinaa.kotlinapp.model.RNoteEntity
import com.emedinaa.kotlinapp.viewmodel.NoteViewModel
import com.emedinaa.kotlinapp.viewmodel.ViewModelFactory

import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity() {

    private lateinit var notes:List<RNoteEntity>

    private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        viewModel =  ViewModelProvider(this,ViewModelFactory(
            Injector.provideRepository(),application)).get(NoteViewModel::class.java)

        ui()

        viewModel.notes.observe(this, Observer {
            notes= it
            lstNotes.adapter= RNoteAdapter(this@NoteListActivity,notes)
        })
    }

    private fun ui(){
        btnAddNote.setOnClickListener {
            goToAddNote()
        }

        lstNotes.setOnItemClickListener { _, _, position, _ ->
            if(notes.isNotEmpty()){
                val note: RNoteEntity = notes[position]
                goToNote(note)
            }
        }
    }


    private fun goToAddNote(){
        startActivity(Intent(this, AddNoteActivity::class.java))
    }

    private fun goToNote(note: RNoteEntity){
        val bundle= Bundle()
        bundle.putSerializable("NOTE",note)
        val intent= Intent(this, EditNoteActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

}
