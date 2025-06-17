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
fun SuccessDialog(
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
                // Success Icon with animation elements
                SuccessIcon()

                Spacer(modifier = Modifier.height(24.dp))

                // "Woo hoo!!" text
                Text(
                    text = "Woo hoo!!",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Congrats message
                Text(
                    text = "Congrats! We have successfully sent your message.",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Done button
                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00D9A6)
                    )
                ) {
                    Text(
                        text = "Done",
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun SuccessIcon() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(
                color = Color(0x1500D9A6),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        // Chat bubble with happy face
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFF00D9A6)),
            contentAlignment = Alignment.Center
        ) {
            // Happy face
            Text(
                text = "◡◠",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        // Small checkmarks and dots around the main circle
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(Color(0xFF00D9A6), CircleShape)
                .align(Alignment.TopEnd)
        )

        Box(
            modifier = Modifier
                .size(16.dp)
                .background(Color(0xFF00D9A6), CircleShape)
                .align(Alignment.BottomStart)
        )

        Text(
            text = "✓",
            fontSize = 16.sp,
            color = Color(0xFF00D9A6),
            modifier = Modifier.align(Alignment.TopStart)
        )

        Text(
            text = "✓",
            fontSize = 16.sp,
            color = Color(0xFF00D9A6),
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SuccessDialogPreview() {
    MaterialTheme {
        Surface(color = Color.White) {
            SuccessDialog(onDismiss = {})
        }
    }
}

// In your actual app, you would use this dialog like:
//@Composable
//fun MainScreen() {
//    // State to control when to show the dialog
//    val showDialog = remember { mutableStateOf(false) }
//
//    // Your app content here
//
//    Button(onClick = { showDialog.value = true }) {
//        Text("Show Success Dialog")
//    }
//
//    if (showDialog.value) {
//        SuccessDialog(onDismiss = { showDialog.value = false })
//    }
//}