package com.m01n008.minirevenuecat.sdk.purchases

import com.m01n008.minirevenuecat.sdk.models.CustomerInfo

interface PurchasesDelegate {
    fun onCustomerInfoUpdated(info: CustomerInfo)
}