package com.emedinaa.kotlinapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tb_notes")
data class RNoteEntity(@PrimaryKey(autoGenerate = true)val id:Int?,
                       @ColumnInfo(name = "name") val name:String?,
                       @ColumnInfo(name = "description") val description:String?):Serializable{

    override fun toString(): String {
        return "Note(id=$id, name=$name, description=$description)"
    }
}