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
fun DeleteConfirmationDialog(
    onConfirmDelete: () -> Unit,
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
                // Delete Icon
                DeleteIcon()

                Spacer(modifier = Modifier.height(24.dp))

                // "Delete message?" text
                Text(
                    text = "Delete message?",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Warning text
                Text(
                    text = "This action cannot be undone. Are you sure you want to delete this message?",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Delete button (using a red color to indicate destructive action)
                Button(
                    onClick = onConfirmDelete,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF5252)
                    )
                ) {
                    Text(
                        text = "Delete",
                        fontSize = 18.sp,
                        color = Color.White
                    )
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
                        contentColor = Color(0xFF00D9A6)
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
fun DeleteIcon() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(
                color = Color(0x15FF5252),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        // Trash icon with outer circle
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFFFF5252)),
            contentAlignment = Alignment.Center
        ) {
            // Trash can symbol
            Text(
                text = "🗑️",
                fontSize = 36.sp,
                color = Color.White
            )
        }

        // Small decorative elements
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(Color(0xFFFF5252), CircleShape)
                .align(Alignment.TopEnd)
        )

        Box(
            modifier = Modifier
                .size(16.dp)
                .background(Color(0xFFFF5252), CircleShape)
                .align(Alignment.BottomStart)
        )

        // Warning symbols
        Text(
            text = "!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFF5252),
            modifier = Modifier.align(Alignment.TopStart)
        )

        Text(
            text = "!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFF5252),
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DeleteConfirmationDialogPreview() {
    MaterialTheme {
        Surface(color = Color.White) {
            DeleteConfirmationDialog(
                onConfirmDelete = {},
                onCancel = {}
            )
        }
    }
}

// In your actual app, you would use this dialog like:
//@Composable
//fun MainScreen() {
//    // State to control when to show the dialog
//    val showDeleteDialog = remember { mutableStateOf(false) }
//
//    // Your app content here
//
//    Button(onClick = { showDeleteDialog.value = true }) {
//        Text("Show Delete Confirmation Dialog")
//    }
//
//    if (showDeleteDialog.value) {
//        DeleteConfirmationDialog(
//            onConfirmDelete = {
//                // Handle the message deletion
//                showDeleteDialog.value = false
//            },
//            onCancel = {
//                showDeleteDialog.value = false
//            }
//        )
//    }
//}