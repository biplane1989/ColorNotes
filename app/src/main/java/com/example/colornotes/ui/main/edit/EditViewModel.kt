package com.example.colornotes.ui.main.edit

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.colornotes.db.INoteRepository
import com.example.colornotes.db.NoteRepository
import com.example.colornotes.db.dao.NoteDAO
import com.example.colornotes.db.entity.Note
import com.example.colornotes.ui.base.BaseViewModel
import com.example.colornotes.ui.main.home.ItemViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class EditViewModel(val noteRepository: INoteRepository) : BaseViewModel(noteRepository) {

    private var _title: MutableLiveData<String> = MutableLiveData()
    private var _content: MutableLiveData<String> = MutableLiveData()
    private var _colorNote: MutableLiveData<Int> = MutableLiveData()


    var title: MutableLiveData<String> = _title
    var content: MutableLiveData<String> = _content
    var colorNote: MutableLiveData<Int> = _colorNote
    lateinit var note: Note

    fun getNote(id: Int) {
        compositeDisposable.add(
            noteRepository.getUserById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    Consumer { t -> updateUI(t) }
                )
        )
    }

    fun updateNote(newNote: Note) {
        editNote(newNote)
        compositeDisposable.add(
            noteRepository.updateUser(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        )
    }

    fun editNote(newNote: Note) {
        this.note.title = newNote.title
        this.note.content = newNote.content
        this.note.color = newNote.color
    }

    fun updateUI(note: Note) {
        title.value = note.title
        content.value = note.content
        colorNote.value = note.color
        this.note = note
    }

    //binding adapter
    fun getColor(): MutableLiveData<Int> {
        return colorNote
    }
}

