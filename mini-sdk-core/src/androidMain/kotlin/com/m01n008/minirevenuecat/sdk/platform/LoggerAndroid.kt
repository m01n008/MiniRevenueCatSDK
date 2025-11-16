package com.m01n008.minirevenuecat.sdk.platform

import android.util.Log

actual object Logger {
    private const val TAG = "MiniRC"

    actual fun d(message: String) {
      Log.d(TAG, message)
    }

    actual fun e(message: String) {
        Log.e(TAG, message)
    }
}