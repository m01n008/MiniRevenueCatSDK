package com.m01n008.minirevenuecat.sdk.models

data class CustomerInfo(
    val entitlements: List<Entitlement>,
    val activeSubscriptions: List<String>,
    val nonRenewingPurchases: List<String>
)