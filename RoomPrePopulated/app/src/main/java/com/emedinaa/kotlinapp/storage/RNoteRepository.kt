package com.emedinaa.kotlinapp.storage

import android.content.Context
import android.os.AsyncTask
import com.emedinaa.kotlinapp.RNoteEntity

class RNoteRepository(context:Context) {

    //private var notes: List<RNoteEntity>? = null
    private var noteDao:RNoteDao?

    init {
        val db = RNoteDataBase.getInstance(context)
        noteDao = db?.noteDao()
    }

    fun getAllNotes(populateCallback: PopulateCallback) {
        //return notes = noteDao.getAll();
        /*new PopulateAsyncTask(noteDao, new PopulateCallback() {
            @Override
            public void onSuccess(List<NoteEntity> noteEntities) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        }).execute();*/
        noteDao?.let {
            PopulateAsyncTask(it, populateCallback).execute()
        }
    }

    fun add(noteEntity: RNoteEntity) {
        //noteDao.insert(noteEntity);
        noteDao?.let {
            InsertAsyncTask(it).execute(noteEntity)
        }
    }

    /**
     * Error
     * Caused by: android.database.sqlite.SQLiteConstraintException: UNIQUE constraint failed: tb_notes.id (code 1555)
     */
    fun addNotes(vararg noteList: RNoteEntity) {
        //noteDao.insertAll(noteList);
        noteDao?.let {
            //InsertEntitiesAsyncTask(it).execute(noteList)
        }
    }


    fun update(noteEntity: RNoteEntity) {
        //noteDao.update(noteEntity);
        noteDao?.let {
            UpdateAsyncTask(it).execute(noteEntity)
        }
    }

    fun delete(noteEntity: RNoteEntity) {
        //noteDao.delete(noteEntity);
        noteDao?.let {
            DeleteAsyncTask(it).execute(noteEntity)
        }
    }


    interface PopulateCallback {
        fun onSuccess(noteEntities: List<RNoteEntity>)
        fun onFailure(e: Exception)
    }

    private class InsertAsyncTask internal constructor(private val mAsyncTaskDao: RNoteDao) : AsyncTask<RNoteEntity, Void, Void>() {

        override fun doInBackground(vararg params: RNoteEntity): Void? {
            mAsyncTaskDao.addNote(params[0])
            return null
        }
    }

    private class UpdateAsyncTask internal constructor(private val mAsyncTaskDao: RNoteDao) : AsyncTask<RNoteEntity, Void, Void>() {

        override fun doInBackground(vararg params: RNoteEntity): Void? {
            mAsyncTaskDao.updateNote(params[0])
            return null
        }
    }

    private class DeleteAsyncTask internal constructor(private val mAsyncTaskDao: RNoteDao) : AsyncTask<RNoteEntity, Void, Void>() {

        override fun doInBackground(vararg params: RNoteEntity): Void? {
            mAsyncTaskDao.deleteNote(params[0])
            return null
        }
    }


    private class InsertEntitiesAsyncTask internal constructor(private val mAsyncTaskDao: RNoteDao) : AsyncTask<RNoteEntity, Void, Void>() {

        override fun doInBackground(vararg params: RNoteEntity): Void? {
            //mAsyncTaskDao.addNotes(params)
            return null
        }
    }

    private class PopulateAsyncTask internal constructor(private val mAsyncTaskDao: RNoteDao, private val populateCallback: PopulateCallback?) : AsyncTask<Void, Void, List<RNoteEntity>>() {

        override fun doInBackground(vararg voids: Void): List<RNoteEntity> {
            return mAsyncTaskDao.notes()
        }

        override fun onPostExecute(noteEntities: List<RNoteEntity>) {
            super.onPostExecute(noteEntities)
            populateCallback?.onSuccess(noteEntities)
        }
    }
}