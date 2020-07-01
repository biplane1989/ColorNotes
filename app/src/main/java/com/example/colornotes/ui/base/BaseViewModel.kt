package com.example.colornotes.ui.base

import androidx.lifecycle.ViewModel
import com.example.colornotes.db.INoteRepository
import com.example.colornotes.db.NoteRepository
import com.example.colornotes.db.dao.NoteDAO
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(private val noteRepository: INoteRepository) : ViewModel() {

    var compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
