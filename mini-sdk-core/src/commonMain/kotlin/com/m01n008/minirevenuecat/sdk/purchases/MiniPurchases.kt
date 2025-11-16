package com.m01n008.minirevenuecat.sdk.purchases

import com.m01n008.minirevenuecat.sdk.api.BackendResult
import com.m01n008.minirevenuecat.sdk.api.PurchasesAPI
import com.m01n008.minirevenuecat.sdk.caches.TTlCache
import com.m01n008.minirevenuecat.sdk.concurrency.AtomicBoolean
import com.m01n008.minirevenuecat.sdk.models.CustomerInfo
import com.m01n008.minirevenuecat.sdk.platform.Logger
import kotlin.time.Duration.Companion.minutes

private val isConfigured = AtomicBoolean(false)
private val isFetching = AtomicBoolean(false)
class MiniPurchases internal constructor(
    private val appUserId: String,
    private val api: PurchasesAPI
) {

    private val cache = TTlCache<String, CustomerInfo>(ttl = 10.minutes)
    suspend fun getCustomerInfo(): CustomerInfo? {
        if (!isFetching.compareAndSet(false, true)){
            Logger.d("Another fetch is already running")
            return null
        }

        try {
            val cached = cache.get("customer_info")
            if (cached != null) return cached

            return when (val result = api.fetchCustomerInfo(appUserId)){
                is BackendResult.Success -> {
                    cache.put("customer_info", result.value)
                    result.value
                }
                is BackendResult.Error -> {
                    Logger.e("Backend error: ${result.message}")
                    null
                }

            }
        } finally {
            isFetching.set(false)
        }

    }


    companion object {
        private var instance: MiniPurchases? = null

        fun configure(apiKey: String, appUserId: String): MiniPurchases? {
            if(!isConfigured.compareAndSet(false, true)) {
                return instance!!
            }

            val newInstance = MiniPurchases(appUserId, PurchasesAPI())
            instance = newInstance
            return instance
        }
    }
}
