package com.example.colornotes.db.dao

import androidx.room.*
import com.example.colornotes.db.entity.Note
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface NoteDAO {

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserById(id: Int): Flowable<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note): Completable

    @Query("SELECT * FROM users")
    fun getAll(): Flowable<List<Note>>

    @Delete
    fun delete(note: Note)

    @Query("DELETE FROM users")
    fun deleteAllUser()

    @Update
    fun updateUser(note: Note): Completable
}