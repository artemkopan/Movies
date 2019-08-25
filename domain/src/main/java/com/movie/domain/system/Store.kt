package com.movie.domain.system

interface Store {

    fun put(key: String, value: Any)

    fun <T> get(key: String): T?

    fun remove(key: String)
}