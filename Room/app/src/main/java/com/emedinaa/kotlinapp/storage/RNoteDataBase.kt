package com.emedinaa.kotlinapp.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.emedinaa.kotlinapp.RNoteEntity

@Database(entities = [RNoteEntity::class], version = 1)
abstract class RNoteDataBase : RoomDatabase() {

    abstract fun noteDao(): RNoteDao

    companion object {
        private var INSTANCE: RNoteDataBase? = null
        private const val DBNAME="BDRoom.db"

        fun getInstance(context: Context): RNoteDataBase? {
            if (INSTANCE == null) {
                synchronized(RNoteDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            RNoteDataBase::class.java, DBNAME)
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}