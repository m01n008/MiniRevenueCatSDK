package com.m01n008.minirevenuecat.sdk.platform



actual object Logger {
    private const val TAG = "MiniRC"

    actual fun d(message: String) {
        println("DEBUG: [$TAG] $message")
    }

    actual fun e(message: String) {
        println("ERROR: [$TAG] $message")
    }
}