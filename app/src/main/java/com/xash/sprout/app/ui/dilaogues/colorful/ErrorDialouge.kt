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
fun ErrorMessageDialog(
    title: String = "Something went wrong!",
    message: String = "An unexpected error occurred. Please try again later.",
    onRetry: (() -> Unit)? = null,
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
                // Error Icon
                ErrorIcon()

                Spacer(modifier = Modifier.height(24.dp))

                // Error title
                Text(
                    text = title,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Error message
                Text(
                    text = message,
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Retry button (if retry action is provided)
                if (onRetry != null) {
                    Button(
                        onClick = onRetry,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF9800)
                        )
                    ) {
                        Text(
                            text = "Try Again",
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                }

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
                        text = if (onRetry != null) "Close" else "OK",
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@Composable
fun ErrorIcon() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(
                color = Color(0x15FF9800),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        // Error icon with outer circle
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFFFF9800)),
            contentAlignment = Alignment.Center
        ) {
            // Error symbol
            Text(
                text = "⚠️",
                fontSize = 36.sp,
                color = Color.White
            )
        }

        // Small decorative elements
        Box(
            modifier = Modifier
                .size(12.dp)
                .background(Color(0xFFFF9800), CircleShape)
                .align(Alignment.TopEnd)
        )

        Box(
            modifier = Modifier
                .size(12.dp)
                .background(Color(0xFFFF9800), CircleShape)
                .align(Alignment.BottomStart)
        )

        // Additional warning symbols
        Text(
            text = "!",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFF9800),
            modifier = Modifier.align(Alignment.TopStart)
        )

        Text(
            text = "!",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFF9800),
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorMessageDialogPreview() {
    MaterialTheme {
        Surface(color = Color.White) {
            ErrorMessageDialog(
                title = "Connection Failed",
                message = "Unable to connect to the server. Please check your internet connection and try again.",
                onRetry = {},
                onDismiss = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorMessageDialogSimplePreview() {
    MaterialTheme {
        Surface(color = Color.White) {
            ErrorMessageDialog(
                onDismiss = {}
            )
        }
    }
}