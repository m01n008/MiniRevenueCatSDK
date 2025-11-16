package com.m01n008.minirevenuecat.sdk.concurrency

expect class AtomicBoolean(initial: Boolean) {
    fun get(): Boolean
    fun set(value: Boolean)
    fun compareAndSet(expected: Boolean, new: Boolean): Boolean
}