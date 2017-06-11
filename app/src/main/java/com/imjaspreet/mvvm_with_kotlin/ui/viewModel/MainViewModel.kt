package com.imjaspreet.mvvm_with_kotlin.ui.viewModel

import android.content.Context
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.imjaspreet.mvvm_with_kotlin.App
import com.imjaspreet.mvvm_with_kotlin.R
import com.imjaspreet.mvvm_with_kotlin.data.Injector
import com.imjaspreet.mvvm_with_kotlin.data.model.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver

/**
 * Created by jaspreet on 10/06/17.
 */

class MainViewModel(var context : Context?, var dataListener : DataListener?) : ViewModel{

    var infoMessageVisibility: ObservableInt
    var progressVisibility: ObservableInt
    var recyclerViewVisibility: ObservableInt
    var searchButtonVisibility: ObservableInt
    var infoMessage: ObservableField<String>

    var disposable: DisposableObserver<Repository>? = null
    var repositories: List<Repository>? = null

    lateinit var editTextUsernameValue: String

    init {
        infoMessageVisibility = ObservableInt(View.VISIBLE)
        progressVisibility = ObservableInt(View.INVISIBLE)
        recyclerViewVisibility = ObservableInt(View.INVISIBLE)
        searchButtonVisibility = ObservableInt(View.GONE)
        infoMessage = ObservableField<String>(context?.getString(R.string.default_info_message))
    }


    override fun destroy() {

        if(!disposable!!.isDisposed) disposable!!.dispose()
        disposable = null
        context = null
        dataListener = null
    }

    fun onSearchAction(view: TextView, actionId: Int, event: KeyEvent): Boolean {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            val username = view.text.toString()
            if (username.isNotEmpty())
                loadRepositories(username)
                return true
        }
        return false
    }

    fun onClickSearch(view: View) {
        loadRepositories(editTextUsernameValue)
    }

    fun getUsernameEditTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
                editTextUsernameValue = charSequence.toString()
                searchButtonVisibility.set(if (charSequence.length > 0) View.VISIBLE else View.GONE)
            }

            override fun afterTextChanged(editable: Editable) {

            }
        }
    }

    private fun loadRepositories(username : String) {

        progressVisibility.set(View.VISIBLE)
        recyclerViewVisibility.set(View.INVISIBLE)
        infoMessageVisibility.set(View.INVISIBLE)
        if(!disposable!!.isDisposed) disposable!!.dispose()
        val injector = Injector()
        val api = injector.provideApi()
        disposable = api.getRepositories(username)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(App.instance?.defaultSubscribeScheduler())
                .subscribeWith(object : DisposableObserver<Repository>() {
                    override fun onNext(value: Repository) {
                        this@MainViewModel.repositories = repositories
                    }

                    override fun onError(e: Throwable) {
                        progressVisibility.set(View.INVISIBLE)
                        infoMessage.set(context?.getString(R.string.error_username_not_found))
                        infoMessageVisibility.set(View.VISIBLE)

                    }

                    override fun onComplete() {
                        dataListener?.onRepositoriesChanged(repositories!!)
                        progressVisibility.set(View.INVISIBLE)
                        if (!repositories?.isEmpty()!!) {
                            recyclerViewVisibility.set(View.VISIBLE)
                        } else {
                            infoMessage.set(context?.getString(R.string.text_empty_repos))
                            infoMessageVisibility.set(View.VISIBLE)
                        }
                    }
                })
    }

    interface DataListener {
        fun onRepositoriesChanged(repositories: List<Repository>)
    }
}
