package com.m01n008.minirevenuecat.sdk.concurrency

import kotlin.concurrent.atomics.AtomicInt
import kotlin.concurrent.atomics.ExperimentalAtomicApi

actual class AtomicBoolean actual constructor(initial: Boolean) {
    @OptIn(ExperimentalAtomicApi::class)
    private val atomic = AtomicInt(if (initial) 1 else 0)

    @OptIn(ExperimentalAtomicApi::class)
    actual fun get(): Boolean = atomic.value == 1
    @OptIn(ExperimentalAtomicApi::class)
    actual fun set(value: Boolean) { atomic.value = if (value) 1 else 0
    }

    @OptIn(ExperimentalAtomicApi::class)
    actual fun compareAndSet(expected: Boolean, new: Boolean): Boolean {
        return atomic.compareAndSet(expectedValue = if(expected) 1 else 0, newValue = if (new) 1 else 0)
    }

}