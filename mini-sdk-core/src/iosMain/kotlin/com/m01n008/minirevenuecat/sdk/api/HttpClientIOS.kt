package com.m01n008.minirevenuecat.sdk.api

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import platform.Foundation.*

@OptIn(ExperimentalForeignApi::class)
actual class HttpClient {
    @OptIn(ExperimentalForeignApi::class)
    actual suspend fun get(url: String, headers: Map<String, String>): String =
        suspendCancellableCoroutine { continuation ->
            val nsUrl = NSURL.URLWithString(url)
                ?: run {
                    continuation.resume("")
                    return@suspendCancellableCoroutine
                }
            val request = NSMutableURLRequest.requestWithURL(nsUrl)
            request.HTTPMethod = "GET"
            headers.forEach { (k, v) -> request.setValue(v, k) }

            val task = NSURLSession.sharedSession.dataTaskWithRequest(request) {
                { data, response, error ->
                    when {
                        error != null -> {
                            continuation.resume("")
                        }

                        data != null -> {
                            val text = NSString.create(data, NSUTF8StringEncoding)?.toString() ?: ""
                            continuation.resume(text)
                        }

                        else -> continuation.resume("")

                    }

                }
                task.resume()
                continuation.invokeOnCancellation {
                    task.cancel()
                }
                }
        }
}