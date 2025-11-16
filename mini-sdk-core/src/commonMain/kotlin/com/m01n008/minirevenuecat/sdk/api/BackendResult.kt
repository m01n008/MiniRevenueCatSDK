package com.m01n008.minirevenuecat.sdk.api

sealed class BackendResult<out T> {
    data class Success<T>(val value: T) : BackendResult<T>()
    data class Error(val message: String, val code: Int): BackendResult<Nothing>()
}