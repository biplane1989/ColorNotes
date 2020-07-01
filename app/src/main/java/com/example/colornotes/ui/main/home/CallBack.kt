package com.example.colornotes.ui.main.home

import android.view.View
import com.example.colornotes.db.entity.Note

interface CallBack {

    fun onItemClicked(note: Note)
    fun onItemLongClicked(note: Note)
}