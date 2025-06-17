package com.xash.sprout.app.ui.dilaogues.colorful

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun MaintenanceDialog(
    title: String = "Scheduled Maintenance",
    message: String = "Our systems are undergoing scheduled maintenance. Services will resume shortly. Thank you for your patience.",
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp, horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Maintenance Icon
                MaintenanceIcon()

                Spacer(modifier = Modifier.height(24.dp))

                // Maintenance title
                Text(
                    text = title,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Maintenance message
                Text(
                    text = message,
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Dismiss button
                OutlinedButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF00D9A6)
                    )
                ) {
                    Text(
                        text = "OK",
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@Composable
fun MaintenanceIcon() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(
                color = Color(0x1500D9A6),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        // Maintenance icon with outer circle
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFF00D9A6)),
            contentAlignment = Alignment.Center
        ) {
            // Maintenance symbol (using a wrench emoji for maintenance)
            Text(
                text = "🔧",
                fontSize = 36.sp,
                color = Color.White
            )
        }

        // Small decorative elements
        Box(
            modifier = Modifier
                .size(12.dp)
                .background(Color(0xFF00D9A6), CircleShape)
                .align(Alignment.TopEnd)
        )

        Box(
            modifier = Modifier
                .size(12.dp)
                .background(Color(0xFF00D9A6), CircleShape)
                .align(Alignment.BottomStart)
        )

        // Additional maintenance symbols
        Text(
            text = "⚙️",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF00D9A6),
            modifier = Modifier.align(Alignment.TopStart)
        )

        Text(
            text = "⚙️",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF00D9A6),
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MaintenanceDialogPreview() {
    MaterialTheme {
        Surface(color = Color.White) {
            MaintenanceDialog(
                title = "Under Maintenance",
                message = "We're performing scheduled maintenance to improve our services. We'll be back soon!",
                onDismiss = {}
            )
        }
    }
}