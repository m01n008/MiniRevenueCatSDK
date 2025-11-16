package com.m01n008.minirevenuecat.sdk.models

data class Entitlement(
    val id: String,
    val isActive: Boolean,
    val expiresAt: Long?
)