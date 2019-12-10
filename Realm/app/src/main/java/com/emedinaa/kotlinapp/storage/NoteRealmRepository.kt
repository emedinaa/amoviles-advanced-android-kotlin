package com.emedinaa.kotlinapp.storage

import com.emedinaa.kotlinapp.NoteRealm
import io.realm.Realm

class NoteRealmRepository(val noteRealmDatabase: NoteRealmDatabase) {

    val realm:Realm = noteRealmDatabase.realm

    fun notes():List<NoteRealm>{
        return realm.where(NoteRealm::class.java).findAll()
    }

    private fun findById(id:Int):NoteRealm?{
        return realm.where(NoteRealm::class.java).equalTo("id", id).findFirst()
    }

    fun addNote(note:NoteRealm){
        realm.executeTransaction {

            val maxId = realm.where(NoteRealm::class.java).max("id")
            val nextId = if (maxId == null) 1 else maxId?.toInt() + 1

            val nNote = it.createObject(NoteRealm::class.java,nextId)
            nNote.name = note.name
            nNote.description = note.description
        }
    }

    fun updateNote(note:NoteRealm){
        val pNote = findById(note.id)

        pNote?.let {itNote ->
            realm.executeTransaction {
                itNote.name= note.name
                itNote.description= note.description
            }
        }

    }

    fun deleteNote(note:NoteRealm){
        val dNote = findById(note.id)

        dNote?.let {itNote ->
            realm.executeTransaction {
                itNote.deleteFromRealm()
            }
        }
    }
}