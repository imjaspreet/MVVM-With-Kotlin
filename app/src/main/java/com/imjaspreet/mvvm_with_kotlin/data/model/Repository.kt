package com.imjaspreet.mvvm_with_kotlin.data.model

import android.os.Parcel
import android.os.Parcelable

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