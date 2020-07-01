package com.example.colornotes.ui.main.view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.colornotes.db.INoteRepository
import com.example.colornotes.db.entity.Note
import com.example.colornotes.ui.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class ViewViewModel(val noteRepository: INoteRepository) : BaseViewModel(noteRepository) {

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

    fun updateUI(note: Note) {
        title.value = note.title
        content.value = note.content
        colorNote.value = note.color
        this.note = note

    }

    fun deleteNote() {
        val disposable =
            Observable.create<Any> { e ->
                noteRepository.delete(note)
                e.onComplete()
            }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(Consumer { t -> Log.d("001", "delete success !") })
        compositeDisposable.add(disposable)
    }

    //binding adapter
    fun getColor(): MutableLiveData<Int> {
        return colorNote
    }

}
