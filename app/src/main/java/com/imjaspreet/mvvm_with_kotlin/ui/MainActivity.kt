package com.imjaspreet.mvvm_with_kotlin.ui

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.imjaspreet.mvvm_with_kotlin.R
import com.imjaspreet.mvvm_with_kotlin.data.model.Repository
import com.imjaspreet.mvvm_with_kotlin.databinding.ActivityMainBinding
import com.imjaspreet.mvvm_with_kotlin.ui.viewModel.MainViewModel

class MainActivity : AppCompatActivity(), MainViewModel.DataListener {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainViewModel = MainViewModel(this, this)
        binding.viewModel = mainViewModel

    }

    override fun onRepositoriesChanged(repositories: List<Repository>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
