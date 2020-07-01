package com.example.colornotes.utils

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.colornotes.ui.main.home.HomeAdapter
import com.example.colornotes.ui.main.home.ItemViewModel

object BidingUtls {
    @BindingAdapter("setAdapter")
    @JvmStatic
    fun setAdapterListUser(view: RecyclerView, list: List<ItemViewModel>) {
        val adapter: HomeAdapter? = view.adapter as HomeAdapter?
        if (adapter != null && list != null && !list.isEmpty()) {
            adapter.setData(list)
        }
    }

    @BindingAdapter("setColor")
    @JvmStatic
    fun setBackgorundColor(view: ConstraintLayout, color: Int) {
        if (view != null) {
            view.setBackgroundColor(Category.getCategory(view.context, color))
        }
    }
}