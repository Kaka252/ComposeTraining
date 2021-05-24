package com.test.compose.utils

class Resource<T>(val status: Int, val data: T?, val msg: String?) {

    companion object {
        const val SUCCESS = 0
        const val ERROR = 1
        const val LOADING = 2

        fun <T> success(data: T) = Resource(SUCCESS, data, null)
        fun <T> error(msg: String) = Resource<T>(ERROR, null, msg)
        fun <T> loading() = Resource<T>(LOADING, null, null)
    }
}