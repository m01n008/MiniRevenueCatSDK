package com.m01n008.minirevenuecat.sdk

import com.m01n008.minirevenuecat.sdk.backoff.ExponentialBackoff
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.milliseconds

class ExponentialBackoffTest {

    @Test
    fun testRetrySucceeds() = runBlocking {
        val backoff  = ExponentialBackoff(initialDelay = 10.milliseconds, maxDelay = 50.milliseconds, maxRetries = 3)
        var attempt = 0
        val result = backoff.retry {
            attempt++
            attempt >= 2
        }
        assertTrue(result)
    }

    @Test
    fun testRetryFails() = runBlocking {
        val backoff = ExponentialBackoff(initialDelay = 10.milliseconds, maxDelay = 50.milliseconds, maxRetries = 2)
        val result = backoff.retry {
            false
        }
        assertTrue(!result)
    }
}