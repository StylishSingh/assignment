package com.books.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

abstract class BaseActivity<DB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity(){


    protected abstract val containerId: Int
    private var progressDialog: Dialog? = null
    private lateinit var imm: InputMethodManager
    var replaceWithNavigation: Boolean = false
    protected abstract val viewModelClass: Class<VM>
    protected open var factory: ViewModelProvider.NewInstanceFactory? = null


    protected lateinit var binding: DB
    protected lateinit var viewModel: VM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, containerId)
        imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        viewModel = when (factory) {
            null -> when (replaceWithNavigation) {
                true -> ViewModelProviders.of(this).get(viewModelClass)
                else -> ViewModelProviders.of(this).get(viewModelClass)
            }
            else -> when (replaceWithNavigation) {
                true -> ViewModelProviders.of(this, factory!!).get(viewModelClass)
                else -> ViewModelProviders.of(this, factory!!).get(viewModelClass)
            }

        }


    }


    /**
     * Use it to show Keyboard
     */
    fun showKeyboard() {
        try {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Use it to hide Keyboard
     * @editText provide if hide for particular editText
     */
    fun hideKeyboard(editText: EditText? = null) {
        if (editText != null && editText.windowToken != null)
            imm.hideSoftInputFromWindow(editText.windowToken, 0)
        if (currentFocus != null && currentFocus!!.windowToken != null)
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }




}