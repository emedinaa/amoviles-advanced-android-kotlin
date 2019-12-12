package com.emedinaa.kotlinapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.emedinaa.kotlinapp.di.Injector
import com.emedinaa.kotlinapp.model.RNoteEntity
import com.emedinaa.kotlinapp.viewmodel.NoteViewModel
import com.emedinaa.kotlinapp.viewmodel.ViewModelFactory

import kotlinx.android.synthetic.main.activity_edit_note.*

class EditNoteActivity : AppCompatActivity(), NoteDialogFragment.DialogListener {

    private var note: RNoteEntity?=null

    private var name:String?=null
    private var desc:String?=null
    private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        verifyExtras()

        viewModel =  ViewModelProvider(this, ViewModelFactory(
            Injector.provideRepository(),application)
        ).get(NoteViewModel::class.java)

        populate()

        ui()
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
       val nNote= RNoteEntity(noteId, name, desc)
       viewModel.update(nNote)
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
            viewModel.delete(it)
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
