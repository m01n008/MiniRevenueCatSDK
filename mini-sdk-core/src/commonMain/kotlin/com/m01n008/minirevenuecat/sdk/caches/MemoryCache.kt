package com.m01n008.minirevenuecat.sdk.caches

class MemoryCache<K, V>{
    private val map = mutableMapOf<K, V>()

    fun get(key: K): V? = map[key]
    fun put(key: K, value: V) { map[key] = value }
    fun clear() = map.clear()
}