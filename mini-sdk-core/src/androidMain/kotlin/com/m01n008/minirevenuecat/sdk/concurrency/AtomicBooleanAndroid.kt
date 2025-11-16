package com.m01n008.minirevenuecat.sdk.concurrency

import java.util.concurrent.atomic.AtomicBoolean as JAtomicBoolean

actual class AtomicBoolean actual constructor(initial: Boolean) {
    private val atomic = JAtomicBoolean(initial)
    actual fun get() = atomic.get()

    actual fun set(value: Boolean) = atomic.set(value)

    actual fun compareAndSet(expected: Boolean, new: Boolean): Boolean =
       atomic.compareAndSet(expected, new)

}