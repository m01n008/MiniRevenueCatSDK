package com.m01n008.minirevenuecatsdk.sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m01n008.minirevenuecat.sdk.models.CustomerInfo
import com.m01n008.minirevenuecat.sdk.purchases.MiniPurchases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MiniViewModel: ViewModel() {
    private val purchases  = MiniPurchases.configure(
        apiKey = "test_api_key",
        appUserId = "Moin_Sample_App_123"
    )

    private val _customerInfo = MutableStateFlow<CustomerInfo?>(null)
    val customerInfo: StateFlow<CustomerInfo?> = _customerInfo

    fun loadCustomerInfo() {
        viewModelScope.launch {
            val info = purchases?.getCustomerInfo()
            _customerInfo.value = info;
        }
    }
}