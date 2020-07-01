package com.example.colornotes.utils

import android.content.Context
import com.example.colornotes.R

object Category {

    fun getCategory(context: Context, counter: Int): Int {
        return when (counter) {
            0 -> context.resources.getColor(R.color.white)
            1 -> context.resources.getColor(R.color.red)
            2 -> context.resources.getColor(R.color.green)
            3 -> context.resources.getColor(R.color.yeallow)
            else -> context.resources.getColor(R.color.white)
        }
    }

}