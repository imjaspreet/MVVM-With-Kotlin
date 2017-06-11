package com.imjaspreet.mvvm_with_kotlin

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Created by jaspreet on 11/06/17.
 */
class App : Application() {

    private var defaultSubscribeScheduler: Scheduler? = null

    override fun onCreate() {
        super.onCreate()

        instance = this

    }

    fun checkIfHasNetwork(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun defaultSubscribeScheduler(): Scheduler? {
        if (defaultSubscribeScheduler == null) {
            defaultSubscribeScheduler = Schedulers.io()
        }
        return defaultSubscribeScheduler
    }

    companion object {

        var instance: App? = null
            private set

        fun hasNetwork(): Boolean {
            return instance!!.checkIfHasNetwork()
        }
    }
}