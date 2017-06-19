package com.imjaspreet.mvpwithkotlin.ui

import com.imjaspreet.mvpwithkotlin.data.model.Repository

/**
 * Created by jaspreet on 19/06/17.
 */
interface MainView : BaseView{

    fun showRepositories(repositories: List<Repository>)

    fun showMessage(stringId: Int)

    fun showProgressIndicator()
}