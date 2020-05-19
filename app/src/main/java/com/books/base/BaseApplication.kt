package com.books.base

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.os.StrictMode

open class BaseApplication : Application() {

    var instance: BaseApplication? = null

    override fun onCreate() {
        super.onCreate()

        //For Picker to avoid FIleUri Exposed Exception
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
    }

    open fun hasNetwork(): Boolean {
        return instance!!.checkIfHasNetwork()
    }


    open fun checkIfHasNetwork(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}