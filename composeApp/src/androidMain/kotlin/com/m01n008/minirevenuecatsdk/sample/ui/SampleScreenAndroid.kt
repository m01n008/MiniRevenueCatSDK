package com.m01n008.minirevenuecatsdk.sample.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.m01n008.minirevenuecat.sdk.models.CustomerInfo
import com.m01n008.minirevenuecatsdk.sample.MiniViewModel
import com.m01n008.minirevenuecatsdk.sample.ui.components.CustomerInfoCard

@Composable
fun MainScreen(viewModel: MiniViewModel = viewModel()){
    val customerInfo: CustomerInfo? by viewModel.customerInfo.collectAsState(initial = null)

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Text(
            modifier = Modifier.padding(top = 24.dp),
            text = "Mini Revenue Cat SDK Sample",
            style = MaterialTheme.typography.headlineSmall
        )

        Button(
            onClick = { viewModel.loadCustomerInfo()}
        ) {
            Text("Fetch Customer Info")
        }

        when {
            customerInfo == null -> Text("No data loaded yet.")
            else -> CustomerInfoCard(customerInfo!!)
        }

        Spacer(modifier = Modifier.weight(1f))

     Text(
         text = "Built with ❤\uFE0F by Moin Khan",
         style = MaterialTheme.typography.bodySmall.copy(
             color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
         ),
         modifier = Modifier
             .padding(12.dp)
             .align(Alignment.CenterHorizontally)
     )
    }
}