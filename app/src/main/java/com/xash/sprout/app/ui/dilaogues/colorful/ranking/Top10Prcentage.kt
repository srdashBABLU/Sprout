package com.xash.sprout.app.ui.dilaogues.colorful.ranking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
fun TopTenPercentDialog(
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(20.dp),
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
                TopTenPercentIcon()

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Top 10% Achiever",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF43A047), // Fresh green tone
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "You're officially among the top 10%!\nGreat things lie ahead — keep pushing forward.",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF43A047)
                    )
                ) {
                    Text(
                        text = "Stay on Track 🚀",
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun TopTenPercentIcon() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(
                color = Color(0x1543A047),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFF43A047)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "🎯", // Precision and achievement
                fontSize = 36.sp,
                color = Color.White
            )
        }

        Box(
            modifier = Modifier
                .size(14.dp)
                .background(Color(0xFF43A047), CircleShape)
                .align(Alignment.TopStart)
        )

        Box(
            modifier = Modifier
                .size(14.dp)
                .background(Color(0xFF43A047), CircleShape)
                .align(Alignment.BottomEnd)
        )

        Text(
            text = "💡",
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.TopEnd)
        )

        Text(
            text = "⏫",
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.BottomStart)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopTenPercentDialogPreview() {
    MaterialTheme {
        Surface(color = Color.White) {
            TopTenPercentDialog(onDismiss = {})
        }
    }
}
