package com.m01n008.minirevenuecatsdk.sample.ui.components

import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.m01n008.minirevenuecat.sdk.models.CustomerInfo

@Composable
fun CustomerInfoCard(info: CustomerInfo?) {
    Card (
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ){
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Entitlements:", style = MaterialTheme.typography.titleMedium)
            info?.entitlements!!.forEach {
                Text("- ${it.id} | Active: ${it.isActive}")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text("Active Subscriptions: ", style = MaterialTheme.typography.titleMedium)
            info?.activeSubscriptions!!.forEach {
                Text("- $it")
            }
        }
    }
}