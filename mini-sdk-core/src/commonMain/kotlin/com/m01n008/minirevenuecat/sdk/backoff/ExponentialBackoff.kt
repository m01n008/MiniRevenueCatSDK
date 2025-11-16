package com.m01n008.minirevenuecat.sdk.backoff

import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import kotlin.random.Random
import kotlin.time.Clock
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime

class ExponentialBackoff(
    private val initialDelay: Duration = 100.milliseconds,
    private val  maxDelay: Duration = 5.seconds,
    private val maxRetries: Int = 5
) {
    @OptIn(ExperimentalTime::class)
    private val random = Random(Clock.System.now().toEpochMilliseconds())

    suspend fun retry(block: suspend () -> Boolean): Boolean {
        var currentDelay = initialDelay
        repeat(maxRetries){
            if(block()){
                return true
            }
            val jitter = random.nextLong(0, currentDelay.inWholeMilliseconds)
            val delayTime = (currentDelay.inWholeMilliseconds + jitter).coerceAtMost(maxDelay.inWholeMilliseconds)
            delay(delayTime)
            currentDelay = (currentDelay * 2).coerceAtMost(maxDelay)
        }
        return false
    }
}