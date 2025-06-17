package com.xash.sprout.app.ui.dilaogues.colorful

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
fun LogoutDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
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
                // Logout Icon
                LogoutIcon()

                Spacer(modifier = Modifier.height(24.dp))

                // "Log out?" text
                Text(
                    text = "Log out?",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Logout confirmation message
                Text(
                    text = "Are you sure you want to log out of your account?",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Buttons: Cancel and Logout
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = onDismiss,
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF0F0F0)
                        )
                    ) {
                        Text(
                            text = "Cancel",
                            fontSize = 18.sp,
                            color = Color.Black
                        )
                    }

                    Button(
                        onClick = onConfirm,
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFAF0931)
                        )
                    ) {
                        Text(
                            text = "Logout",
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LogoutIcon() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(
                color = Color(0x15007AFF),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        // Logout symbol
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFF007AFF)),
            contentAlignment = Alignment.Center
        ) {
            // Arrow symbol for logout
            Text(
                text = "→",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        // Small indicators around the main circle
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(Color(0xFF007AFF), CircleShape)
                .align(Alignment.TopEnd)
        )

        Box(
            modifier = Modifier
                .size(16.dp)
                .background(Color(0xFF007AFF), CircleShape)
                .align(Alignment.BottomStart)
        )

        Text(
            text = "•",
            fontSize = 16.sp,
            color = Color(0xFF007AFF),
            modifier = Modifier.align(Alignment.TopStart)
        )

        Text(
            text = "•",
            fontSize = 16.sp,
            color = Color(0xFF007AFF),
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LogoutDialogPreview() {
    MaterialTheme {
        Surface(color = Color.White) {
            LogoutDialog(
                onDismiss = {},
                onConfirm = {}
            )
        }
    }
}