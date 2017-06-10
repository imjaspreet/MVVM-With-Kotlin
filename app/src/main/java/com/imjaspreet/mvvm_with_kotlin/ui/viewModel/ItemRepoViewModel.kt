package com.imjaspreet.mvvm_with_kotlin.ui.viewModel

import android.view.View

/**
 * Created by jaspreet on 10/06/17.
 */
class ItemRepoViewModel : ViewModel{


    fun getName(): String {
        return "demo"
    }

    fun getDescription(): String {
        return "demo"
    }

    fun getStars(): String {
        return "demo"
    }

    fun getWatchers(): String {
        return "demo"
    }

    fun getForks(): String {
        return "demo"
    }

    fun onItemClick(view: View) {

    }

    override fun destroy() {

    }
}