package com.emedinaa.kotlinapp

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

data class Note(val id:Int?, val name:String?,val description:String?):Serializable{

    override fun toString(): String {
        return "Note(id=$id, name=$name, description=$description)"
    }
}

open class NoteRealm(
    @PrimaryKey var id:Int=-1,
    var name:String?=null,
    var description:String?=null
    ):RealmObject()