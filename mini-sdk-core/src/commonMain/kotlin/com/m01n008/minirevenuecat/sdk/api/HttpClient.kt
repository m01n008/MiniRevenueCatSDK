package com.m01n008.minirevenuecat.sdk.api

expect class HttpClient() {
    suspend fun get(url: String, headers: Map<String, String> = emptyMap()): String
}