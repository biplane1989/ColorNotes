package com.example.colornotes.ui.main.home

import com.example.colornotes.db.entity.Note

interface AlertCallBack {
    fun handleNegativeAlertCallBack()
    fun handlePositiveAlertCallBack(note: Note)

}