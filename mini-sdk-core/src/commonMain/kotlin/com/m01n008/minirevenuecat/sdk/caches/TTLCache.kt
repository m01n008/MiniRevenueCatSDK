package com.m01n008.minirevenuecat.sdk.caches

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.time.Duration
import kotlin.time.TimeSource

class TTlCache<K, V>(
    private val ttl: Duration
){
    private val mutex = Mutex()
    private val cache = mutableMapOf<K, CacheEntry<V>>()
    private val timeSource = TimeSource.Monotonic

    private data class CacheEntry<V> (
        val value: V,
        val timestamp: Long
        )

    suspend fun put(key: K, value: V) {
        mutex.withLock {
            cache[key] = CacheEntry(value, timeSource.markNow().elapsedNow().inWholeMilliseconds)
        }
    }

    suspend fun get(key: K) {
        return mutex.withLock {
            val now = timeSource.markNow().elapsedNow().inWholeMilliseconds
            val entry = cache[key]
            if (entry != null && (now - entry.timestamp) <= ttl.inWholeMilliseconds) {
                entry.value
            } else {
                cache.remove(key)
                null
            }
        }
    }

    suspend fun clear(){
        mutex.withLock {
            cache.clear()
        }
    }
}