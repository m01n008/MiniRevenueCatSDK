package com.m01n008.minirevenuecat.sdk

import com.m01n008.minirevenuecat.sdk.caches.TTlCache
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.seconds

class TTLCacheTest {

    @Test
    fun testPutAndGet() = runBlocking {
        val cache = TTlCache<String, String>(ttl = 2.seconds)
        cache.put("key1", "value1")
        val value = cache.get("key1").toString()
        assertEquals("value1", value)
    }
}