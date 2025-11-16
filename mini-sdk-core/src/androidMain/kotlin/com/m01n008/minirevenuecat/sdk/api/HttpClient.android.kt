package com.m01n008.minirevenuecat.sdk.api

actual class HttpClient actual constructor() {
    actual suspend fun get(
        url: String,
        headers: Map<String, String>
    ): String {
        TODO("Not yet implemented")
    }
}