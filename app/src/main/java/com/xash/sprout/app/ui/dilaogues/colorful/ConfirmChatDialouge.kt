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
import androidx.compose.foundation.layout.width
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
fun ConfirmChatDialog(
    userName: String = "User",
    userAvatar: String? = null,
    onConfirmChat: () -> Unit,
    onCancel: () -> Unit
) {
    Dialog(onDismissRequest = onCancel) {
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
                // Chat Icon
                ChatIcon()

                Spacer(modifier = Modifier.height(24.dp))

                // "Start chat?" text
                Text(
                    text = "Start chat?",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(12.dp))

                // User info and confirmation text
                Text(
                    text = "Would you like to start a conversation with $userName?",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Start Chat button
                Button(
                    onClick = onConfirmChat,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00D9A6)
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "💬",
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Start Chat",
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Cancel button
                OutlinedButton(
                    onClick = onCancel,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.Gray
                    )
                ) {
                    Text(
                        text = "Cancel",
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@Composable
fun ChatIcon() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(
                color = Color(0x1500D9A6),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        // Chat icon with outer circle
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFF00D9A6)),
            contentAlignment = Alignment.Center
        ) {
            // Chat bubble symbol
            Text(
                text = "💬",
                fontSize = 36.sp,
                color = Color.White
            )
        }

        // Small decorative message bubbles
        Box(
            modifier = Modifier
                .size(14.dp)
                .background(Color(0xFF00D9A6), CircleShape)
                .align(Alignment.TopEnd)
        )

        Box(
            modifier = Modifier
                .size(14.dp)
                .background(Color(0xFF00D9A6), CircleShape)
                .align(Alignment.BottomStart)
        )

        // Connection symbols
        Text(
            text = "🔗",
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.TopStart)
        )

        Text(
            text = "✨",
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

// Alternative version with user profile info
@Composable
fun ConfirmChatWithProfileDialog(
    userName: String = "John Doe",
    userStatus: String = "Online",
    onConfirmChat: () -> Unit,
    onCancel: () -> Unit
) {
    Dialog(onDismissRequest = onCancel) {
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
                // User Profile Icon
                UserProfileIcon()

                Spacer(modifier = Modifier.height(24.dp))

                // User name
                Text(
                    text = userName,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                // User status
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(Color(0xFF4CAF50), CircleShape)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = userStatus,
                        fontSize = 14.sp,
                        color = Color(0xFF4CAF50),
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Confirmation text
                Text(
                    text = "Start a conversation with this user?",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Start Chat button
                Button(
                    onClick = onConfirmChat,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00D9A6)
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "💬",
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Start Chat",
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Cancel button
                OutlinedButton(
                    onClick = onCancel,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.Gray
                    )
                ) {
                    Text(
                        text = "Cancel",
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@Composable
fun UserProfileIcon() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(
                color = Color(0x1500D9A6),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        // User profile circle
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFF00D9A6)),
            contentAlignment = Alignment.Center
        ) {
            // User avatar placeholder
            Text(
                text = "👤",
                fontSize = 36.sp,
                color = Color.White
            )
        }

        // Online indicator
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(Color.White, CircleShape)
                .align(Alignment.BottomEnd)
        ) {
            Box(
                modifier = Modifier
                    .size(14.dp)
                    .background(Color(0xFF4CAF50), CircleShape)
                    .align(Alignment.Center)
            )
        }

        // Decorative elements
        Text(
            text = "✨",
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.TopStart)
        )

        Text(
            text = "💭",
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ConfirmChatDialogPreview() {
    MaterialTheme {
        Surface(color = Color.White) {
            ConfirmChatDialog(
                userName = "Alice Johnson",
                onConfirmChat = {},
                onCancel = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConfirmChatWithProfileDialogPreview() {
    MaterialTheme {
        Surface(color = Color.White) {
            ConfirmChatWithProfileDialog(
                userName = "John Doe",
                userStatus = "Online",
                onConfirmChat = {},
                onCancel = {}
            )
        }
    }
}