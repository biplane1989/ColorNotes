package com.example.colornotes.ui.main.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.colornotes.BR
import com.example.colornotes.R
import com.example.colornotes.databinding.HomeFragmentBinding
import com.example.colornotes.db.entity.Note
import com.example.colornotes.ui.ViewModelFactory
import com.example.colornotes.ui.base.BaseFragment


class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>(), CallBack {

    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelFactory = noteRepository?.let { ViewModelFactory(it) }!!
    }

    override fun getLayoutId(): Int {
        return R.layout.home_fragment
    }

    override fun getViewModel(): HomeViewModel {
//        if (mViewModel == null) {
        mViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
//        }
        return mViewModel
    }

    override fun getBindingVariable(): Int {
        return BR.homeviewmodel
    }

    override fun initLayout() {
        setUpAdapter()
        onEvent()
    }

    fun setUpAdapter() {
        val adapter = context?.let { HomeAdapter(it, viewLifecycleOwner, this) }
//        mBinding.rvNotes.layoutManager =
//            GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        mBinding.rvNotes.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

//        bBinding.rvNumber.layoutManager =
//            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mBinding.rvNotes.setHasFixedSize(true)
        mBinding.rvNotes.adapter = adapter
    }

    fun onEvent() {
        mBinding.fab.setOnClickListener(View.OnClickListener {
            val directions = HomeFragmentDirections.actionHoneToAdd()
            NavHostFragment.findNavController(this).navigate(directions)
        })
    }

    override fun onItemClicked(note: Note) {
        val directions = HomeFragmentDirections.actionHomeToView().setID(note.id)
        NavHostFragment.findNavController(this).navigate(directions)
    }

    override fun onItemLongClicked(note: Note) {
        Log.d("001", "loong click")
        showAlert(getString(R.string.alert_title), R.string.ok, R.string.cancel, note)
    }

    override fun handlePositiveAlertCallBack(note: Note) {
        super.handlePositiveAlertCallBack(note)
        mViewModel.deleteNote(note)
        showToast(getString(R.string.delete))
    }
}