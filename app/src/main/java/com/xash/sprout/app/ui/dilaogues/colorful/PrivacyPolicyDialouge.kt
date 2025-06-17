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
import androidx.compose.material3.Button
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
fun PrivacyPolicyDialog(
    onAcknowledge: () -> Unit,
    onDecline: () -> Unit
) {
    Dialog(onDismissRequest = onDecline) {
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
                // Privacy Policy Icon
                PrivacyPolicyIcon()

                Spacer(modifier = Modifier.height(24.dp))

                // "Privacy Policy" text
                Text(
                    text = "Privacy Policy",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Description text
                Text(
                    text = "Please review and acknowledge our Privacy Policy to understand how we collect, use, and protect your personal information.",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Acknowledge button
                Button(
                    onClick = onAcknowledge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1E88E5)
                    )
                ) {
                    Text(
                        text = "Acknowledge",
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Decline button
                OutlinedButton(
                    onClick = onDecline,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF1E88E5)
                    )
                ) {
                    Text(
                        text = "Decline",
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@Composable
fun PrivacyPolicyIcon() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(
                color = Color(0x151E88E5),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        // Privacy Policy icon with outer circle
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFF1E88E5)),
            contentAlignment = Alignment.Center
        ) {
            // Privacy Policy symbol (using an emoji for simplicity)
            Text(
                text = "🔒",
                fontSize = 36.sp,
                color = Color.White
            )
        }

        // Small decorative elements
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(Color(0xFF1E88E5), CircleShape)
                .align(Alignment.TopEnd)
        )

        Box(
            modifier = Modifier
                .size(16.dp)
                .background(Color(0xFF1E88E5), CircleShape)
                .align(Alignment.BottomStart)
        )

        // Privacy indicators
        Text(
            text = "🛡️",  // Shield symbol for protection
            fontSize = 18.sp,
            color = Color(0xFF1E88E5),
            modifier = Modifier.align(Alignment.TopStart)
        )

        Text(
            text = "🔐",  // Lock symbol for security
            fontSize = 18.sp,
            color = Color(0xFF1E88E5),
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrivacyPolicyDialogPreview() {
    MaterialTheme {
        Surface(color = Color.White) {
            PrivacyPolicyDialog(
                onAcknowledge = {},
                onDecline = {}
            )
        }
    }
}