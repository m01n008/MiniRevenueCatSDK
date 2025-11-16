package com.m01n008.minirevenuecat.sdk

import com.m01n008.minirevenuecat.sdk.purchases.MiniPurchases
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertNull

class MiniPurchasesTest {

    @Test
    fun testGetCustomerInfoReturnsNullByDefault() = runBlocking {
        val purchases  = MiniPurchases.configure("test_api_key", "user123")
        val result = purchases?.getCustomerInfo()
        assertNull(result)
    }
}
