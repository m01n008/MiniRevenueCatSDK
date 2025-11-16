package com.m01n008.minirevenuecat.sdk.api

import okhttp3.OkHttpClient
import okhttp3.Request

actual class HttpClient {
    private val client = OkHttpClient()
    actual suspend fun get(url: String, headers: Map<String, String>): String{
        val request = Request.Builder()
            .url(url)
            .apply {
                headers.forEach { (k, v) -> addHeader(k,v) }
            }
            .build()

        val response = client.newCall(request).execute()
        return response.body?.toString() ?: ""
    }



}
