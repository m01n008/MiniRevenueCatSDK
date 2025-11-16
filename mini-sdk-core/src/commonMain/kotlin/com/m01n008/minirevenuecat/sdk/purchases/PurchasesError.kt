package com.m01n008.minirevenuecat.sdk.purchases

sealed class PurchasesError(val message: String) {
    object NetworkError : PurchasesError("Network error")
    object BackendError : PurchasesError("Backend issue")
    object UnknownError : PurchasesError("Unknown error")
}