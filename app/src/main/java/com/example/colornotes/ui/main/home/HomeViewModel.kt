package com.example.colornotes.ui.main.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.colornotes.db.INoteRepository
import com.example.colornotes.db.entity.Note
import com.example.colornotes.ui.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class HomeViewModel(val noteRepository: INoteRepository) : BaseViewModel(noteRepository) {

    private val _listNote: MutableLiveData<MutableList<ItemViewModel>> by lazy {
        MutableLiveData<MutableList<ItemViewModel>>()
    }

    var listNote: MutableLiveData<MutableList<ItemViewModel>> = _listNote
    var list: MutableList<ItemViewModel> = mutableListOf<ItemViewModel>()

    init {
        listNote.value = list
        updateListNote()
    }

    fun updateListNote() {
        compositeDisposable.add(
            noteRepository.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    Consumer { arrNote: List<Note> ->
                        list.clear()
                        listNote.postValue(list)
                        for (note in arrNote) {
                            list.add(ItemViewModel(note))
                        }
                        listNote.postValue(list)
                    }
                )
        )
    }

    fun deleteNote(note: Note) {
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

    // bindingAdapter
    fun getData(): MutableLiveData<MutableList<ItemViewModel>> {
        return listNote
    }
}