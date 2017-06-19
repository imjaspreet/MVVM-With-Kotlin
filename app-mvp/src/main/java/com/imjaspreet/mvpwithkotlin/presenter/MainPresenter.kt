package com.imjaspreet.mvpwithkotlin.presenter

import com.imjaspreet.mvpwithkotlin.App
import com.imjaspreet.mvpwithkotlin.R
import com.imjaspreet.mvpwithkotlin.data.Injector
import com.imjaspreet.mvpwithkotlin.data.model.Repository
import com.imjaspreet.mvpwithkotlin.ui.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver


/**
 * Created by jaspreet on 19/06/17.
 */

class MainPresenter : BasePresenter<MainView>{

    private var mainView: MainView? = null
    private var disposable: DisposableObserver<List<Repository>>? = null
    private var repositories: List<Repository>? = null

    override fun attachView(view: MainView) {
        this.mainView = view;
    }

    override fun detachView() {
        this.mainView = null;
        if(!disposable!!.isDisposed) disposable!!.dispose()
        disposable = null
    }

    private fun loadRepositories(usernameEntered : String) {

        val username = usernameEntered.trim()
        if (username.isEmpty()) return

        mainView?.showProgressIndicator()

        val injector = Injector()
        val api = injector.provideApi()
        disposable = api.getRepositories(username)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(App.instance?.defaultSubscribeScheduler())
                .subscribeWith(LongOperationObserver())
    }

    private inner class LongOperationObserver : DisposableObserver<List<Repository>>() {

        override fun onNext(value: List<Repository>) {
            this@MainPresenter.repositories = value
        }

        override fun onError(e: Throwable) {
            mainView?.showMessage(R.string.error_username_not_found);

        }

        override fun onComplete() {
            if (!repositories?.isEmpty()!!) {
                mainView?.showRepositories(repositories!!);
            } else {
                mainView?.showMessage(R.string.text_empty_repos);
            }
        }
    }

}
