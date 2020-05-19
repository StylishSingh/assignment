package com.books.base


import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.ScrollView
import android.widget.Toast
import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes
import androidx.annotation.DimenRes
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView

abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    protected abstract val fragmentLayoutId: Int
    protected abstract val viewModelClass: Class<VM>
    protected open var factory: ViewModelProvider.NewInstanceFactory? = null

    var replaceWithNavigation: Boolean = false

    protected lateinit var binding: DB
    protected lateinit var viewModel: VM
    /**
     * To send Data From Fragment to its parent Activity
     */
    /**
     * Additional bundle to avoid setArgument(only once) issue in fragment
     */
    var bundle: Bundle? = null



    /**
     * Call only one time when attach to activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, fragmentLayoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setLifecycleOwner(this)

        viewModel = when (factory) {
            null -> when (replaceWithNavigation && activity != null) {
                true -> ViewModelProviders.of(requireActivity()).get(viewModelClass)
                else -> ViewModelProviders.of(this).get(viewModelClass)
            }
            else -> when (replaceWithNavigation && activity != null) {
                true -> ViewModelProviders.of(requireActivity(), factory!!).get(viewModelClass)
                else -> ViewModelProviders.of(this, factory!!).get(viewModelClass)
            }

        }

    }

    protected fun hideKeyboard(editText: EditText? = null) {
        (activity as? BaseActivity<*, *>)?.hideKeyboard(editText)
    }


}