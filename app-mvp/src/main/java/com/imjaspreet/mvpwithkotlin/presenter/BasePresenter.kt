package com.imjaspreet.mvpwithkotlin.presenter

import android.icu.lang.UCharacter.GraphemeClusterBreak.V



/**
 * Created by jaspreet on 19/06/17.
 */
interface BasePresenter<V> {

    fun attachView(view: V)

    fun detachView()
}