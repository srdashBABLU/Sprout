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
fun SuspiciousUserAlertDialog(
    userName: String = "Unknown User",
    suspiciousActivity: String = "unusual behavior",
    onBlockUser: () -> Unit,
    onReportUser: () -> Unit,
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
                // Suspicious User Icon
                SuspiciousUserIcon()

                Spacer(modifier = Modifier.height(24.dp))

                // "Suspicious Activity" text
                Text(
                    text = "Suspicious Activity Detected",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Warning message
                Text(
                    text = "We've detected $suspiciousActivity from $userName. This user may pose a security risk.",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Additional warning
                Text(
                    text = "Please proceed with caution or take action to protect yourself.",
                    fontSize = 14.sp,
                    color = Color(0xFFFF5722),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Block User button
                Button(
                    onClick = onBlockUser,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF5252)
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "🚫",
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Block User",
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Report button
                Button(
                    onClick = onReportUser,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF9800)
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "📢",
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Report User",
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Dismiss button
                OutlinedButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.Gray
                    )
                ) {
                    Text(
                        text = "Dismiss",
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@Composable
fun SuspiciousUserIcon() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(
                color = Color(0x15FF5722),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        // User icon with warning overlay
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFFFF5722)),
            contentAlignment = Alignment.Center
        ) {
            // Suspicious user symbol
            Text(
                text = "👤",
                fontSize = 36.sp,
                color = Color.White
            )
        }

        // Warning overlay
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(Color(0xFFFF9800), CircleShape)
                .align(Alignment.TopEnd)
        ) {
            Text(
                text = "⚠️",
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Shield/security symbols
        Text(
            text = "🛡️",
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.TopStart)
        )

        Text(
            text = "🔍",
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.BottomStart)
        )

        // Danger indicator
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(Color(0xFFFF5252), CircleShape)
                .align(Alignment.BottomEnd)
        )
    }
}

// Alternative version for specific security threats
@Composable
fun SecurityThreatAlertDialog(
    threatType: String = "Phishing Attempt",
    threatDescription: String = "This user has been flagged for sending suspicious links",
    onTakeAction: () -> Unit,
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
                // Security Threat Icon
                SecurityThreatIcon()

                Spacer(modifier = Modifier.height(24.dp))

                // Threat type
                Text(
                    text = threatType,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFD32F2F),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Threat description
                Text(
                    text = threatDescription,
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Security advice
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFF3E0)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "💡",
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Never share personal information or click suspicious links.",
                            fontSize = 14.sp,
                            color = Color(0xFFE65100),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Take Action button
                Button(
                    onClick = onTakeAction,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD32F2F)
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "🛡️",
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Take Action",
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Dismiss button
                OutlinedButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.Gray
                    )
                ) {
                    Text(
                        text = "I'll Be Careful",
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@Composable
fun SecurityThreatIcon() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(
                color = Color(0x15D32F2F),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        // Shield with warning
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFFD32F2F)),
            contentAlignment = Alignment.Center
        ) {
            // Shield symbol
            Text(
                text = "🛡️",
                fontSize = 36.sp,
                color = Color.White
            )
        }

        // Warning indicators
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(Color(0xFFFF9800), CircleShape)
                .align(Alignment.TopEnd)
        ) {
            Text(
                text = "!",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Security symbols
        Text(
            text = "🔒",
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.TopStart)
        )

        Text(
            text = "⚠️",
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.BottomStart)
        )

        Text(
            text = "🚨",
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SuspiciousUserAlertDialogPreview() {
    MaterialTheme {
        Surface(color = Color.White) {
            SuspiciousUserAlertDialog(
                userName = "John_Suspicious",
                suspiciousActivity = "sending multiple spam messages",
                onBlockUser = {},
                onReportUser = {},
                onDismiss = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecurityThreatAlertDialogPreview() {
    MaterialTheme {
        Surface(color = Color.White) {
            SecurityThreatAlertDialog(
                threatType = "Phishing Attempt",
                threatDescription = "This user has been flagged for sending suspicious links that may steal your personal information.",
                onTakeAction = {},
                onDismiss = {}
            )
        }
    }
}