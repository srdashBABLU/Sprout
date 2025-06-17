package com.xash.sprout.app.ui.dilaogues.colorful

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun UserExperienceRatingDialog(
    onRatingSubmit: (rating: Int, feedback: String) -> Unit,
    onCancel: () -> Unit
) {
    var selectedRating by remember { mutableStateOf(0) }
    var feedbackText by remember { mutableStateOf("") }
    var showFeedbackField by remember { mutableStateOf(false) }

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
                // Rating Icon
                RatingIcon()

                Spacer(modifier = Modifier.height(24.dp))

                // "Rate Your Experience" text
                Text(
                    text = "Rate Your Experience",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1976D2),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Description message
                Text(
                    text = "Your feedback helps us improve and provide better service for everyone.",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Star Rating
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF8F9FA)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "How would you rate your experience?",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF1976D2),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Star Rating Row
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            repeat(5) { index ->
                                val starNumber = index + 1
                                val isSelected = starNumber <= selectedRating

                                Box(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clickable {
                                            selectedRating = starNumber
                                            showFeedbackField = true
                                        }
                                        .background(
                                            color = if (isSelected) Color(0x20FFD700) else Color.Transparent,
                                            shape = CircleShape
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = if (isSelected) "⭐" else "☆",
                                        fontSize = 32.sp,
                                        color = if (isSelected) Color(0xFFFFD700) else Color(0xFFCCCCCC)
                                    )
                                }
                            }
                        }

                        if (selectedRating > 0) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = getRatingText(selectedRating),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = getRatingColor(selectedRating)
                            )
                        }
                    }
                }

                // Feedback section (appears after rating)
                if (showFeedbackField) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFF3E5F5)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "💬",
                                    fontSize = 20.sp
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Tell us more (optional)",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF6A1B9A)
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            OutlinedTextField(
                                value = feedbackText,
                                onValueChange = { feedbackText = it },
                                placeholder = {
                                    Text(
                                        text = "Share your thoughts...",
                                        color = Color.Gray
                                    )
                                },
                                modifier = Modifier.fillMaxWidth(),
                                maxLines = 3,
                                shape = RoundedCornerShape(8.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Action buttons
                if (selectedRating > 0) {
                    Button(
                        onClick = { onRatingSubmit(selectedRating, feedbackText) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50)
                        )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "✉️",
                                fontSize = 18.sp
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Submit Rating",
                                fontSize = 18.sp,
                                color = Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                }

                // Cancel/Maybe Later button
                OutlinedButton(
                    onClick = onCancel,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF666666)
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "⏰",
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Maybe Later",
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RatingIcon() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(
                color = Color(0x151976D2),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        // Main rating symbol
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFF1976D2)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "⭐",
                fontSize = 36.sp,
                color = Color.White
            )
        }

        // Feedback symbols around the main icon
        Text(
            text = "👤",
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.TopStart)
        )

        Text(
            text = "💭",
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.TopEnd)
        )

        Text(
            text = "📝",
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.BottomStart)
        )

        Text(
            text = "❤️",
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

fun getRatingText(rating: Int): String {
    return when (rating) {
        1 -> "Poor"
        2 -> "Fair"
        3 -> "Good"
        4 -> "Very Good"
        5 -> "Excellent"
        else -> ""
    }
}

fun getRatingColor(rating: Int): Color {
    return when (rating) {
        1 -> Color(0xFFE53935)
        2 -> Color(0xFFFF9800)
        3 -> Color(0xFFFFC107)
        4 -> Color(0xFF8BC34A)
        5 -> Color(0xFF4CAF50)
        else -> Color.Gray
    }
}

// Quick rating dialog for simple feedback
@Composable
fun QuickRatingDialog(
    onRatingSelect: (rating: Int) -> Unit,
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
                    .padding(vertical = 24.dp, horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Quick rating icon
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(
                            color = Color(0x151976D2),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "⭐",
                        fontSize = 28.sp,
                        color = Color(0xFF1976D2)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Quick Rating",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1976D2),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "How was your experience?",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Quick rating options
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    listOf(
                        Pair("😞", "Poor"),
                        Pair("😐", "Okay"),
                        Pair("😊", "Good"),
                        Pair("😍", "Great")
                    ).forEachIndexed { index, (emoji, label) ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clickable { onRatingSelect(index + 1) }
                                .padding(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(56.dp)
                                    .background(
                                        color = Color(0xFFF5F5F5),
                                        shape = CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = emoji,
                                    fontSize = 28.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = label,
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedButton(
                    onClick = onCancel,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Skip",
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserExperienceRatingDialogPreview() {
    MaterialTheme {
        Surface(color = Color.White) {
            UserExperienceRatingDialog(
                onRatingSubmit = { _, _ -> },
                onCancel = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuickRatingDialogPreview() {
    MaterialTheme {
        Surface(color = Color.White) {
            QuickRatingDialog(
                onRatingSelect = {},
                onCancel = {}
            )
        }
    }
}