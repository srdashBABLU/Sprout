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
fun RankTwoAppreciationDialog(
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp, horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                RankTwoMedalIcon()

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "You’re Ranked #2!",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF33691E),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Amazing effort! You’ve secured the second position with style and consistency. Keep pushing!",
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
                        containerColor = Color(0xFF558B2F)
                    )
                ) {
                    Text(
                        text = "Awesome!",
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun RankTwoMedalIcon() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(color = Color(0x15558B2F), shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFF558B2F)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "🥈", // Silver medal emoji
                fontSize = 36.sp,
                color = Color.White
            )
        }

        // Decorative elements
        Box(
            modifier = Modifier
                .size(14.dp)
                .background(Color(0xFF558B2F), CircleShape)
                .align(Alignment.TopStart)
        )

        Box(
            modifier = Modifier
                .size(14.dp)
                .background(Color(0xFF558B2F), CircleShape)
                .align(Alignment.BottomEnd)
        )

        Text(
            text = "💪",
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.TopEnd)
        )
        Text(
            text = "🌿",
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.BottomStart)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RankTwoAppreciationDialogPreview() {
    MaterialTheme {
        Surface(color = Color.White) {
            RankTwoAppreciationDialog(onDismiss = {})
        }
    }
}
