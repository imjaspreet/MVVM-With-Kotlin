package com.imjaspreet.mvvm_with_kotlin.ui

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.inputmethod.InputMethodManager
import com.imjaspreet.mvvm_with_kotlin.R
import com.imjaspreet.mvvm_with_kotlin.data.model.Repository
import com.imjaspreet.mvvm_with_kotlin.databinding.ActivityMainBinding
import com.imjaspreet.mvvm_with_kotlin.ui.viewModel.MainViewModel

class MainActivity : AppCompatActivity(), MainViewModel.DataListener {

    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainViewModel = MainViewModel(this, this)
        binding.viewModel = mainViewModel
        setupRecyclerView(binding.reposRecyclerView)

    }

    override fun onRepositoriesChanged(repositories: List<Repository>) {
        val adapter = binding.reposRecyclerView.adapter as RepositoryAdapter
        adapter.setRepositories(repositories)
        adapter.notifyDataSetChanged()
        hideSoftKeyboard()
    }

    override fun onDestroy() {
        super.onDestroy()
        mainViewModel.destroy()
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        val adapter = RepositoryAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun hideSoftKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.editTextUsername.windowToken, 0)
    }

}
