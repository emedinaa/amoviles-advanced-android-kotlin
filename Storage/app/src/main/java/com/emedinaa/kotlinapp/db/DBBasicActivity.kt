package com.emedinaa.kotlinapp.db

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.emedinaa.kotlinapp.R
import kotlinx.android.synthetic.main.activity_dbbasic.*
import java.lang.StringBuilder

class DBBasicActivity : AppCompatActivity() {

    private var sb:StringBuilder= StringBuilder("")
    private lateinit var noteRepository:BNoteRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dbbasic)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        db()

        btnEdit.setOnClickListener {
            //editar nota
            /*val uNote= Note(3,"Nota 3","Desc Nota 3")
            val row=noteRepository.updateNote(uNote)
            Log.v("CONSOLE", "row update $row")*/
            retrieveNotes()
        }

        btnDelete.setOnClickListener {
            //eliminar nota
            //noteRepository.deleteNote()
            /*val dNote= Note(4,"Nota 2","Nota 2")
            val row= noteRepository.deleteNote(dNote)
            Log.v("CONSOLE", "row $row")*/
            retrieveNotes()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun db(){
        noteRepository= BNoteRepository(BNoteDatabase(this))

        //agregar notas
        noteRepository.addNote(BNote(null,"Nota 1", "Nota 1"))
        noteRepository.addNote(BNote(null,"Nota 2", "Nota 2"))


        //listar notas
        val notes:List<BNote> = noteRepository.notes()
        notes.forEach {
            Log.v("CONSOLE", "note $it")
            sb.appendln("note $it")
        }
        textView.text= sb.toString()

    }

    private fun retrieveNotes(){
        sb.clear()
        val notes:List<BNote> = noteRepository.notes()
        notes.forEach {
            Log.v("CONSOLE", "note $it")
            sb.appendln("note $it")
        }
        textView.text= sb.toString()
    }


}
