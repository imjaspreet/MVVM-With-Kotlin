package com.imjaspreet.mvpwithkotlin.data.model

import com.imjaspreet.mvvm_with_kotlin.data.model.User

/**
 * Created by jaspreet on 10/06/17.
 */
class Repository {

    var id: Long = 0
    var name: String? = null
    var description: String? = null
    var forks: Int = 0
    var watchers: Int = 0
    var stars: Int = 0
    var language: String? = null
    var homepage: String? = null
    var owner: User? = null
    var isFork: Boolean = false
}