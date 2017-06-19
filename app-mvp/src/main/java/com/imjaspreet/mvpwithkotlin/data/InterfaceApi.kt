package com.imjaspreet.mvpwithkotlin.data

import com.imjaspreet.mvpwithkotlin.data.model.Repository
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by jaspreet on 10/06/17.
 */
interface InterfaceApi {


    @GET("users/{username}/repos")
    fun getRepositories(@Path("username") username: String): Observable<List<Repository>>

}