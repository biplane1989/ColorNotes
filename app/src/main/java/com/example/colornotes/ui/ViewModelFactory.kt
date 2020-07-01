package com.example.colornotes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.colornotes.db.INoteRepository
import com.example.colornotes.db.NoteRepository
import com.example.colornotes.db.dao.NoteDAO
import com.example.colornotes.ui.main.add.AddViewModel
import com.example.colornotes.ui.main.edit.EditViewModel
import com.example.colornotes.ui.main.home.HomeViewModel
import com.example.colornotes.ui.main.view.ViewViewModel

class ViewModelFactory(private val noteRepository: INoteRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(noteRepository) as T
        } else if (modelClass.isAssignableFrom(AddViewModel::class.java)) {
            return AddViewModel(noteRepository) as T
        } else if (modelClass.isAssignableFrom(EditViewModel::class.java)) {
            return EditViewModel(noteRepository) as T
        } else if (modelClass.isAssignableFrom(ViewViewModel::class.java)) {
            return ViewViewModel(noteRepository) as T
        } else
            throw IllegalArgumentException("Unknown ViewModel class")
    }
}
