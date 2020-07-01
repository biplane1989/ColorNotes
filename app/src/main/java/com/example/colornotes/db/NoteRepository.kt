package com.example.colornotes.db

import android.content.Context
import androidx.room.Room
import com.example.colornotes.db.entity.Note
import io.reactivex.Completable
import io.reactivex.Flowable

class NoteRepository(context: Context) : INoteRepository {

    companion object {
        private var INSTANCE: NoteRepository? = null

        fun getInstance(context: Context?): NoteRepository {
            if (INSTANCE == null) {
                INSTANCE = context?.let { NoteRepository(it) }
            }
            return INSTANCE as NoteRepository
        }
    }

    val database = NoteDatabase.getInstance(context)

    override fun getUserById(id: Int): Flowable<Note> {
        return database.noteDao().getUserById(id)
    }

    override fun insert(note: Note): Completable {
        return database.noteDao().insert(note)
    }

    override fun getAll(): Flowable<List<Note>> {
        return database.noteDao().getAll()
    }

    override fun delete(note: Note) {
        return database.noteDao().delete(note)
    }

    override fun deleteAllUser() {
        return database.noteDao().deleteAllUser()
    }

    override fun updateUser(note: Note): Completable {
        return database.noteDao().updateUser(note)
    }
}