package com.xash.sprout.app.experimental.api

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    val apiResponse by viewModel.apiResponse.collectAsState()

    Surface(modifier = Modifier.fillMaxSize()) {
        apiResponse?.let { data ->
            Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center) {
                Text("Status: ${data.status}", style = MaterialTheme.typography.titleMedium)
                Text("Message: ${data.message}")
                Text("Developer: ${data.developer}")
                Text("Year: ${data.year}")

                Spacer(Modifier.height(16.dp))
                Text("Features:", style = MaterialTheme.typography.titleSmall)

                LazyColumn {
                    items(data.features.size) { index ->
                        Text("- ${data.features[index]}")
                    }
                }
            }
        } ?: run {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}
