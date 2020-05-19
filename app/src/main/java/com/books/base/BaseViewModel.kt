package com.books.base


import android.os.Parcelable
import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


open class BaseViewModel : ViewModel() {

    var toast = MutableLiveData<Int>()
    var toastMsg = MutableLiveData<String>()
    var keyboard = MutableLiveData<Boolean>()


    override fun onCleared() {
        super.onCleared()
    }

    protected fun showToast(@StringRes message: Int) {
        toast.postValue(message)
    }

    protected fun showToast(message: String) {
        toastMsg.postValue(message)
    }

    protected fun showKeyboard() {
        keyboard.postValue(true)
    }

    protected fun hideKeyboard() {
        keyboard.postValue(false)
    }

    /**
     * A creator is used to inject the product ID into the ViewModel
     *
     *
     * This creator is to showcase how to inject dependencies into ViewModels. It's not
     * actually necessary in this case, as the product ID can be passed in a public method.
     */
    abstract class Factory : ViewModelProvider.NewInstanceFactory() {
        abstract fun getClassInstance(): BaseViewModel

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return getClassInstance() as T
        }
    }


}