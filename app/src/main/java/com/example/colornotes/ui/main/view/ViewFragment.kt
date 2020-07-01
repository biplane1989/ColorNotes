package com.example.colornotes.ui.main.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.example.colornotes.BR
import com.example.colornotes.R
import com.example.colornotes.databinding.ViewFragmentBinding
import com.example.colornotes.ui.ViewModelFactory
import com.example.colornotes.ui.base.BaseFragment

class ViewFragment : BaseFragment<ViewFragmentBinding, ViewViewModel>() {

    lateinit var viewModelFactory: ViewModelFactory
    private var idNote: Int = 0

    override fun getLayoutId(): Int {
        return R.layout.view_fragment
    }

    override fun getViewModel(): ViewViewModel {

        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(ViewViewModel::class.java)
        return mViewModel
    }

    override fun getBindingVariable(): Int {
        return BR.viewviewmodel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelFactory = noteRepository.let { ViewModelFactory(it) }!!

        idNote = arguments?.let { ViewFragmentArgs.fromBundle(it).id }!!
        Log.d("001", "id : " + idNote.toString())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.getNote(idNote)

    }

    override fun initLayout() {
        onEvent()
    }

    fun onEvent() {

        mBinding.fabEdit.setOnClickListener {
            val directions = ViewFragmentDirections.actionViewToEdit().setID(idNote)
            NavHostFragment.findNavController(this).navigate(directions)
        }
        mBinding.fabDelete.setOnClickListener {

            mViewModel.deleteNote()

            val directions = ViewFragmentDirections.actionViewToHome()
            NavHostFragment.findNavController(this).navigate(directions)
        }
    }
}