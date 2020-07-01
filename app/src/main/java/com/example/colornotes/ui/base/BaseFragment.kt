package com.example.colornotes.ui.base

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.colornotes.db.INoteRepository
import com.example.colornotes.db.NoteDatabase
import com.example.colornotes.db.NoteRepository
import com.example.colornotes.db.entity.Note
import com.example.colornotes.ui.main.MainActivity
import com.example.colornotes.ui.main.home.AlertCallBack

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment(), AlertCallBack {

    private lateinit var mCallBackAlertDialog: AlertDialog
    private lateinit var mRootView: View
    protected lateinit var mViewModel: V
    protected lateinit var mBinding: T

    lateinit var noteRepository: INoteRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mRootView = mBinding.root
        initLayout()

        getViewModel()

        return mRootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteRepository = NoteRepository.getInstance(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.setVariable(getBindingVariable(), mViewModel)
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()
    }

    open fun getViewDataBinding(): T {
        return mBinding
    }

    open fun hideKeyboard() {
        if (activity != null) {
            (activity as MainActivity).hideKeyboard()
        }
    }

    fun showToast(msg: String) {
        val toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    fun showAlert(message: String, positiveBtnText: Int, negativeBtnText: Int, note: Note) {
        val dialogBuilder = AlertDialog.Builder(this!!.context!!)
        dialogBuilder.setMessage(message)

        dialogBuilder.setPositiveButton(positiveBtnText) { dialog, whichButton ->
            handlePositiveAlertCallBack(
                note
            )
        }
        dialogBuilder.setNegativeButton(negativeBtnText) { dialog, whichButton -> handleNegativeAlertCallBack() }
        mCallBackAlertDialog = dialogBuilder.create()
        mCallBackAlertDialog.setCancelable(true)
        mCallBackAlertDialog.show()
    }

    override fun handleNegativeAlertCallBack() {
        mCallBackAlertDialog.dismiss()
    }

    override fun handlePositiveAlertCallBack(note: Note) {
        mCallBackAlertDialog.dismiss()
    }

    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): V

    abstract fun getBindingVariable(): Int

    abstract fun initLayout()
}