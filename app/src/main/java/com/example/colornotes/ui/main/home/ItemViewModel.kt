package com.example.colornotes.ui.main.home

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.colornotes.db.entity.Note

class ItemViewModel(val notes: Note) : ViewModel() {

    val note: ObservableField<Note> = ObservableField()

    init {
        this.note.set(notes)
    }

}