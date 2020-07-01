package com.example.colornotes.ui.main.edit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.example.colornotes.BR
import com.example.colornotes.R
import com.example.colornotes.databinding.EditFragmentBinding
import com.example.colornotes.db.NoteDatabase
import com.example.colornotes.db.entity.Note
import com.example.colornotes.ui.ViewModelFactory
import com.example.colornotes.ui.base.BaseFragment
import com.example.colornotes.ui.base.BaseViewModel
import com.example.colornotes.ui.main.MainActivity
import com.example.colornotes.ui.main.home.HomeFragmentDirections
import com.example.colornotes.ui.main.view.ViewFragmentArgs
import kotlinx.android.synthetic.main.add_fragment.*
import kotlinx.android.synthetic.main.edit_fragment.*
import kotlinx.android.synthetic.main.edit_fragment.et_content
import kotlinx.android.synthetic.main.edit_fragment.et_title

class EditFragment : BaseFragment<EditFragmentBinding, EditViewModel>() {

    lateinit var viewModelFactory: ViewModelFactory
    private var idNote = -1
    private var color: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelFactory = noteRepository?.let { ViewModelFactory(it) }!!

        idNote = arguments?.let { ViewFragmentArgs.fromBundle(it).id }!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.getNote(idNote)

    }

    override fun getLayoutId(): Int {
        return R.layout.edit_fragment
    }

    override fun getViewModel(): EditViewModel {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(EditViewModel::class.java)
        return mViewModel
    }

    override fun getBindingVariable(): Int {
        return BR.editviewmodel
    }

    override fun initLayout() {
        onEvent()
    }

    fun onEvent() {
        mBinding.fabRed.setOnClickListener {
            color = 1
            mViewModel.colorNote.value = 1
        }
        mBinding.fabGreen.setOnClickListener {
            color = 2
            mViewModel.colorNote.value = 2
        }
        mBinding.fabYeallow.setOnClickListener {
            color = 3
            mViewModel.colorNote.value = 3
        }
        mBinding.fabWhite.setOnClickListener {
            color = 0
            mViewModel.colorNote.value = 0
        }

        mBinding.fabEdit.setOnClickListener {
            hideKeyboard()

            mViewModel.updateNote(getNote())
            showToast(getString(R.string.edit))
            val directions = EditFragmentDirections.actionEditToHome()
            NavHostFragment.findNavController(this).navigate(directions)
        }
    }

    fun getNote(): Note {
        var title = et_title.text.toString()
        var content = et_content.text.toString()

        return Note(title = title, content = content, color = color)
    }
}