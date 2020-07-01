package com.example.colornotes.db

import androidx.room.*
import com.example.colornotes.db.entity.Note
import io.reactivex.Completable
import io.reactivex.Flowable

interface INoteRepository {

    fun getUserById(id: Int): Flowable<Note>

    fun insert(note: Note): Completable

    fun getAll(): Flowable<List<Note>>

    fun delete(note: Note)

    fun deleteAllUser()

    fun updateUser(note: Note) : Completable
}