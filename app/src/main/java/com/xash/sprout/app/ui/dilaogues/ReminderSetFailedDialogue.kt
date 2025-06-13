package com.xash.sprout.app.ui.dilaogues

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.outlined.Info
import androidx.compose.ui.tooling.preview.Preview
import com.xash.sprout.ui.theme.*

@Composable
fun ReminderSetFailedDialog(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String = "Please check your input and try again."
) {
    val isDarkTheme = isSystemInDarkTheme()
    val containerColor = if (isDarkTheme) red900 else red50
    val onContainerColor = if (isDarkTheme) red100 else red800
    val iconColor = if (isDarkTheme) red400 else red600
    val buttonColor = if (isDarkTheme) red700 else red600
    val onButtonColor = if (isDarkTheme) red50 else red50

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = modifier,
            shape = MaterialTheme.shapes.extraLarge,
            colors = CardDefaults.cardColors(
                containerColor = containerColor
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Error",
                    modifier = Modifier.size(48.dp),
                    tint = iconColor
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Reminder Failed!",
                    style = MaterialTheme.typography.headlineSmall,
                    color = onContainerColor,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = errorMessage,
                    style = MaterialTheme.typography.bodyMedium,
                    color = onContainerColor.copy(alpha = 0.9f),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                FilledTonalButton(
                    onClick = onDismiss,
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = buttonColor,
                        contentColor = onButtonColor
                    ),
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Try Again", style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FailedPrev(){
    MaterialTheme {
        ReminderSetFailedDialog({}, Modifier)
    }
}