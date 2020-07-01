package com.example.colornotes.ui.main.add

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.example.colornotes.BR
import com.example.colornotes.R
import com.example.colornotes.databinding.AddFragmentBinding
import com.example.colornotes.databinding.EditFragmentBinding
import com.example.colornotes.db.entity.Note
import com.example.colornotes.ui.ViewModelFactory
import com.example.colornotes.ui.base.BaseFragment
import com.example.colornotes.ui.main.edit.EditFragmentDirections
import com.example.colornotes.ui.main.edit.EditViewModel
import com.example.colornotes.ui.main.view.ViewFragmentArgs
import kotlinx.android.synthetic.main.add_fragment.*

class AddFragment : BaseFragment<AddFragmentBinding, AddViewModel>() {

    lateinit var viewModelFactory: ViewModelFactory
    private var color: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelFactory = noteRepository?.let { ViewModelFactory(it) }!!
    }

    override fun getLayoutId(): Int {
        return R.layout.add_fragment
    }

    override fun getViewModel(): AddViewModel {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(AddViewModel::class.java)
        return mViewModel
    }

    override fun getBindingVariable(): Int {
        return BR.addviewmodel
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

        mBinding.fabAdd.setOnClickListener {
            hideKeyboard()
            mViewModel.addNote(getNote())
            showToast(getString(R.string.add))
            val directions = AddFragmentDirections.actionAddToHome()
            NavHostFragment.findNavController(this).navigate(directions)
        }
    }

    fun getNote(): Note {
        var title = et_title.text.toString()
        var content = et_content.text.toString()

        return Note(title = title, content = content, color = color)
    }
}