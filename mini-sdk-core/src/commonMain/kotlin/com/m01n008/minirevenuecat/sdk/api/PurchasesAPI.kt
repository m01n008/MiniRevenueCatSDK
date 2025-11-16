package com.m01n008.minirevenuecat.sdk.api

import com.m01n008.minirevenuecat.sdk.models.CustomerInfo
import com.m01n008.minirevenuecat.sdk.models.Entitlement
import com.m01n008.minirevenuecat.sdk.platform.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class PurchasesAPI(
    private val httpClient: HttpClient = HttpClient()
){

    suspend fun fetchCustomerInfo(appUserId: String): BackendResult<CustomerInfo> =
        withContext(Dispatchers.Default) {
            Logger.d("Fetching customer info for $appUserId")
            delay(200)
            return@withContext BackendResult.Success(
                CustomerInfo(
                    entitlements = listOf(
                        Entitlement("pro", true, null),
                        Entitlement("premium", false, null)
                    ),
                    activeSubscriptions = listOf("pro_monthly"),
                    nonRenewingPurchases = emptyList()
                )
            )

//            return@withContext BackendResult.Error(
//                message = "Not Implemented",
//                code = 501
//            )
        }
}