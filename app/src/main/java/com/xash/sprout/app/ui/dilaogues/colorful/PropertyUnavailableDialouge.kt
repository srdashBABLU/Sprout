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
fun PropertyUnavailableDialog(
    onExploreAlternatives: () -> Unit,
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
                // Property Unavailable Icon
                PropertyUnavailableIcon()

                Spacer(modifier = Modifier.height(24.dp))

                // "Property Unavailable" text
                Text(
                    text = "Property Unavailable",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Description text
                Text(
                    text = "The property you selected is no longer available. Explore other options or contact support for assistance.",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Explore Alternatives button
                Button(
                    onClick = onExploreAlternatives,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF616161)
                    )
                ) {
                    Text(
                        text = "Explore Alternatives",
                        fontSize = 18.sp,
                        color = Color.White
                    )
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
                        contentColor = Color(0xFF616161)
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
fun PropertyUnavailableIcon() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(
                color = Color(0x15616161),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        // Property Unavailable icon with outer circle
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFF616161)),
            contentAlignment = Alignment.Center
        ) {
            // Property Unavailable symbol (using an emoji for simplicity)
            Text(
                text = "🏠",
                fontSize = 36.sp,
                color = Color.White
            )
        }

        // Small decorative elements
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(Color(0xFF616161), CircleShape)
                .align(Alignment.TopEnd)
        )

        Box(
            modifier = Modifier
                .size(16.dp)
                .background(Color(0xFF616161), CircleShape)
                .align(Alignment.BottomStart)
        )

        // Property Unavailable indicators
        Text(
            text = "✘",  // Cross symbol for unavailability
            fontSize = 18.sp,
            color = Color(0xFF616161),
            modifier = Modifier.align(Alignment.TopStart)
        )

        Text(
            text = "!",  // Exclamation symbol for alert
            fontSize = 18.sp,
            color = Color(0xFF616161),
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PropertyUnavailableDialogPreview() {
    MaterialTheme {
        Surface(color = Color.White) {
            PropertyUnavailableDialog(
                onExploreAlternatives = {},
                onDismiss = {}
            )
        }
    }
}