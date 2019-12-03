package com.emedinaa.kotlinapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.emedinaa.kotlinapp.storage.RNoteRepository

import kotlinx.android.synthetic.main.activity_edit_note.*

class EditNoteActivity : AppCompatActivity(), NoteDialogFragment.DialogListener {

    private lateinit var noteRepository: RNoteRepository
    private var note:RNoteEntity?=null

    private var name:String?=null
    private var desc:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        verifyExtras()
        setupRepository()
        populate()

        ui()
    }

    private fun setupRepository(){
        //noteRepository= NoteRepository(NoteDatabase(this))
        noteRepository= (application as RNoteApplication).getNoteRepository()
    }

    private fun ui(){
        btnEditNote.setOnClickListener {
            if(validateForm()){
                editNote()
            }
        }

        btnDeleteNote.setOnClickListener {
            showNoteDialog()
        }
    }

    private fun editNote(){
       val noteId= note?.id
       val nNote= RNoteEntity(noteId,name,desc)
       noteRepository.update(nNote)
       finish()
    }

    private fun validateForm():Boolean{
        name= eteName.text.toString()
        desc= eteDesc.text.toString()

        if(name.isNullOrEmpty()){
            return false
        }

        if(desc.isNullOrEmpty()){
            return false
        }

        return true
    }

    private fun populate(){
        note?.let {
            eteName.setText(it.name)
            eteDesc.setText(it.description)
        }
    }

    private fun showNoteDialog(){
        val noteDialogFragment= NoteDialogFragment()
        val bundle= Bundle()
        bundle.putString("TITLE","¿Deseas eliminar esta nota?")
        bundle.putInt("TYPE",100)

        noteDialogFragment.arguments= bundle
        noteDialogFragment.show(supportFragmentManager,"dialog")
    }

    override fun onPositiveListener(any: Any?, type: Int) {
        note?.let {
            noteRepository.delete(it)
        }
        finish()
    }

    override fun onNegativeListener(any: Any?, type: Int) {}

    private fun verifyExtras(){
        intent?.extras?.let {
            note= it.getSerializable("NOTE") as RNoteEntity
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
