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
        val timestamp: TimeSource.Monotonic.ValueTimeMark
        )

    suspend fun put(key: K, value: V) {
        mutex.withLock {
            cache[key] = CacheEntry(value, timeSource.markNow())
        }
    }

    suspend fun get(key: K): V? {
        return mutex.withLock {

            val entry = cache[key]
            if (entry != null &&  entry.timestamp.elapsedNow() <= ttl) {
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