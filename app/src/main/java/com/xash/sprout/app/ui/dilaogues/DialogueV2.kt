package com.xash.sprout.app.ui.dilaogues

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.xash.sprout.ui.theme.green100
import com.xash.sprout.ui.theme.green300
import com.xash.sprout.ui.theme.green400
import com.xash.sprout.ui.theme.green50
import com.xash.sprout.ui.theme.green500
import com.xash.sprout.ui.theme.green600
import com.xash.sprout.ui.theme.green900
import com.xash.sprout.ui.theme.green950

@Composable
fun ReminderSetSuccessDialog2(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isDarkTheme = isSystemInDarkTheme()
    val containerColor = if (isDarkTheme) green900 else green50
    val onContainerColor = if (isDarkTheme) green100 else green900
    val iconColor = if (isDarkTheme) green300 else green500
    val buttonColor = if (isDarkTheme) green400 else green600
    val onButtonColor = if (isDarkTheme) green950 else green50

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
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = "Success",
                    modifier = Modifier.size(48.dp),
                    tint = iconColor
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Reminder Set!",
                    style = MaterialTheme.typography.headlineSmall,
                    color = onContainerColor,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Your reminder has been successfully set.",
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
                    Text("Got it", style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PrevV2(){
    MaterialTheme {
        ReminderSetSuccessDialog2({}, Modifier)
    }
}