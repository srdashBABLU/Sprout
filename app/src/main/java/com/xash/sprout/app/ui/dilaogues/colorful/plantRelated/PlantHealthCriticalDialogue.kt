package com.xash.sprout.app.ui.dilaogues.colorful.plantRelated

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.xash.sprout.R

@Composable
fun PlantHealthCriticalDialog(
    plantName: String = "Monstera",
    onDismiss: () -> Unit,
    onAction: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(24.dp),
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
                    .padding(vertical = 28.dp, horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Critical plant icon
                CriticalPlantIcon()

                Spacer(modifier = Modifier.height(20.dp))

                // Title with plant name
                Text(
                    text = "$plantName Health Critical!",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFD32F2F),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Health warning message
                Text(
                    text = "Your plant is showing critical signs of distress. Immediate action is required to prevent permanent damage.",
                    fontSize = 15.sp,
                    color = Color(0xFF616161),
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(28.dp))

                // Action buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = onDismiss,
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF5F5F5)
                        )
                    ) {
                        Text(
                            text = "Dismiss",
                            fontSize = 16.sp,
                            color = Color(0xFF424242)
                        )
                    }

                    Button(
                        onClick = onAction,
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF388E3C) // Healthy plant green
                        )
                    ) {
                        Text(
                            text = "Fix Now",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CriticalPlantIcon() {
    Box(
        modifier = Modifier
            .size(110.dp)
            .background(
                color = Color(0x15D32F2F), // Light red background
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        // Main plant icon with warning
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFFD32F2F)), // Alert red
            contentAlignment = Alignment.Center
        ) {
            // Plant icon with warning badge
            Box(contentAlignment = Alignment.TopEnd) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Plant",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )

                // Warning badge
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFFC107)) // Warning yellow
                        .padding(4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Warning",
                        tint = Color.Black,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }

        // Decorative elements around the main icon
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(Color(0xFFD32F2F), CircleShape)
                .align(Alignment.TopEnd)
        )

        Box(
            modifier = Modifier
                .size(16.dp)
                .background(Color(0xFFD32F2F), CircleShape)
                .align(Alignment.BottomStart)
        )

        // Small plant icons
        Icon(
            imageVector = Icons.Default.Home,
            contentDescription = null,
            tint = Color(0xFFD32F2F),
            modifier = Modifier
                .size(18.dp)
                .rotate(-15f)
                .align(Alignment.TopStart)
        )

        Icon(
            imageVector = Icons.Default.Home,
            contentDescription = null,
            tint = Color(0xFFD32F2F),
            modifier = Modifier
                .size(18.dp)
                .rotate(25f)
                .align(Alignment.BottomEnd)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PlantHealthCriticalDialogPreview() {
    MaterialTheme {
        Surface(color = Color(0xFFF5F5F5)) {
            PlantHealthCriticalDialog(
                plantName = "Plant",
                onDismiss = {},
                onAction = {}
            )
        }
    }
}