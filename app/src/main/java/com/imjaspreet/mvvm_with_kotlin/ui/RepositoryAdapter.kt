package com.imjaspreet.mvvm_with_kotlin.ui

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.imjaspreet.mvvm_with_kotlin.R
import com.imjaspreet.mvvm_with_kotlin.data.model.Repository
import com.imjaspreet.mvvm_with_kotlin.databinding.ItemRepoBinding
import com.imjaspreet.mvvm_with_kotlin.ui.viewModel.ItemRepoViewModel
import java.util.*

/**
 * Created by jaspreet on 13/06/17.
 */
class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    private var repositories: List<Repository> = Collections.emptyList()

    fun setRepositories(repositories: List<Repository>) {
        this.repositories = repositories
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemRepoBinding>(LayoutInflater.from(parent?.context), R.layout.item_repo, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindRepository(repositories.get(position))
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    class ViewHolder(var binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.cardView) {

        internal fun bindRepository(repository: Repository) {
            if (binding.viewModel == null) {
                binding.viewModel = ItemRepoViewModel(itemView.context, repository)
            } else {
                ItemRepoViewModel.setRepository(binding.viewModel, repository)
            }
        }
    }

}