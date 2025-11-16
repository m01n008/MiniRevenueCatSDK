package com.m01n008.minirevenuecat.sdk.platform

import platform.Foundation.NSLog

actual object Logger {
    actual fun d(message: String) {
        NSLog("MiniRC DEBUG: %@", message)
    }

    actual fun e(message: String) {
        NSLog("MiniRC ERROR: %@", message)
    }
}