package com.imjaspreet.mvpwithkotlin.ui

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.imjaspreet.mvpwithkotlin.R
import com.imjaspreet.mvpwithkotlin.data.model.Repository

class MainActivity : AppCompatActivity() , MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun getContext(): Context {
        TODO(reason = "not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showRepositories(repositories: List<Repository>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMessage(stringId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgressIndicator() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
