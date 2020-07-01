package com.example.colornotes.ui.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.colornotes.R
import com.example.colornotes.databinding.ItemNoteBinding
import com.example.colornotes.utils.Category

class HomeAdapter(
    val context: Context,
    val lifecycleOwner: LifecycleOwner,
    val callBack: CallBack
) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    var list: List<ItemViewModel>

    init {
        list = ArrayList<ItemViewModel>()
    }

    fun setData(list: List<ItemViewModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setBinding(data: ItemViewModel) {
            binding.itemviewmodel = data
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemNoteBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent?.context),
            R.layout.item_note,
            parent,
            false
        )
        binding.lifecycleOwner = lifecycleOwner
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        holder.setBinding(list[position])

        holder.binding.itemCard.setBackgroundColor(
            Category.getCategory(context, list[position].notes.color)
        )

        holder.binding.itemCard.setOnClickListener {
            callBack.onItemClicked(list[position].notes)
        }
        holder.binding.itemCard.setOnLongClickListener {
            callBack.onItemLongClicked(list[position].notes)
            return@setOnLongClickListener true

        }
    }
}