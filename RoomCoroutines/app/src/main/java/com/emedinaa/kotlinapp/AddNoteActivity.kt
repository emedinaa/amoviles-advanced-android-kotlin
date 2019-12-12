package com.emedinaa.kotlinapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.emedinaa.kotlinapp.di.Injector
import com.emedinaa.kotlinapp.model.RNoteEntity
import com.emedinaa.kotlinapp.viewmodel.NoteViewModel
import com.emedinaa.kotlinapp.viewmodel.ViewModelFactory

import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {


    private var name:String?=null
    private var desc:String?=null
    private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel =  ViewModelProvider(this, ViewModelFactory(
            Injector.provideRepository(),application)
        ).get(NoteViewModel::class.java)

        ui()
    }


    private fun ui(){
        btnAddNote.setOnClickListener {
            if(validateForm()){
                addNote()
                finish()
            }
        }
    }

    private fun addNote(){
        val note= RNoteEntity(null, name, desc)
        viewModel.add(note)
    }

    private fun clearForm(){
        eteName.error=null
        eteDesc.error=null
    }

    private fun validateForm():Boolean{
        clearForm()
        name= eteName.text.toString().trim()
        desc= eteDesc.text.toString().trim()

        if(name.isNullOrEmpty()){
            eteName.error="Campo nombre inválido"
            return false
        }

        if(desc.isNullOrEmpty()){
            eteDesc.error="Campo descripción inválido"
            return false
        }

        return true
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
