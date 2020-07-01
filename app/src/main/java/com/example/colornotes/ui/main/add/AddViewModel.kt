package com.example.colornotes.ui.main.add

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.colornotes.db.INoteRepository
import com.example.colornotes.db.entity.Note
import com.example.colornotes.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AddViewModel(val noteRepository: INoteRepository) : BaseViewModel(noteRepository) {

    private var _colorNote: MutableLiveData<Int> = MutableLiveData()

    var colorNote: MutableLiveData<Int> = _colorNote

    fun addNote(note: Note) {
        compositeDisposable.add(
            noteRepository.insert(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("001", "insert success")
                }, { error -> Log.e("001", "insert false", error) })
        )
    }

    //binding adapter
    fun getColor(): MutableLiveData<Int> {
        return colorNote
    }
}