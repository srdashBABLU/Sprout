package com.xash.sprout.app.ui.dilaogues

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DemoDialogueTest(){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var showErrorDialog by remember { mutableStateOf(false) }

        if (showErrorDialog) {
            ReminderSetFailedDialog(
                onDismiss = { showErrorDialog = false },
                errorMessage = "Couldn't access calendar permissions. Please enable in settings."
            )
        }

        var showDialog by remember { mutableStateOf(false) }

        if (showDialog) {
            ReminderSetSuccessDialog(onDismiss = { showDialog = false })
        }

        var showDialog2 by remember { mutableStateOf(false) }

        if (showDialog2) {
            ReminderSetSuccessDialog2(onDismiss = { showDialog2 = false })
        }

        // To show the dialog:
        Button(onClick = { showDialog = true }) {
            Text("Set Reminder")
        }

        // To show the dialog:
        Button(onClick = { showDialog2 = true }) {
            Text("Set Reminder2")
        }

        // Trigger dialog
        Button(onClick = { showErrorDialog = true }) {
            Text("Simulate Failure")
        }
    }
}