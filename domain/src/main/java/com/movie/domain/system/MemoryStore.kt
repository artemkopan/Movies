package com.movie.domain.system

import java.util.concurrent.ConcurrentHashMap

class MemoryStore : Store {

    private val storeMap = ConcurrentHashMap<String, Any>()

    override fun put(key: String, value: Any) {
        storeMap[key] = value
    }

    override fun <T> get(key: String): T? {
        return storeMap[key] as? T?
    }

    override fun remove(key: String) {
        storeMap.remove(key)
    }
}