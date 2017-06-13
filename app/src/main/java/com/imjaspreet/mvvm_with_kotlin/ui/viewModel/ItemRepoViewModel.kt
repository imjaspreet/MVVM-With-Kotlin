package com.imjaspreet.mvvm_with_kotlin.ui.viewModel

import android.content.Context
import android.databinding.BaseObservable
import android.view.View
import com.imjaspreet.mvvm_with_kotlin.R
import com.imjaspreet.mvvm_with_kotlin.data.model.Repository

/**
 * Created by jaspreet on 10/06/17.
 */
class ItemRepoViewModel(var context : Context, var repository : Repository) : BaseObservable(), ViewModel{


    fun getName(): String? {
        return repository.name
    }

    fun getDescription(): String? {
        return repository.description
    }

    fun getStars(): String {
        return context.getString(R.string.text_stars, repository.stars)
    }

    fun getWatchers(): String {
        return context.getString(R.string.text_watchers, repository.watchers)
    }

    fun getForks(): String {
        return context.getString(R.string.text_forks, repository.forks)
    }


    fun onItemClick(view: View) {

    }

    override fun destroy() {

    }

    companion object {
        // Allows recycling ItemRepoViewModels within the recyclerview adapter
        fun setRepository(itemRepoViewModel: ItemRepoViewModel, repository: Repository) {
            itemRepoViewModel.repository = repository
            itemRepoViewModel.notifyChange()
        }
    }
}