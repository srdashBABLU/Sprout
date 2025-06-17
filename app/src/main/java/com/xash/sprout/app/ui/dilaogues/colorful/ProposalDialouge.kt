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
fun ArchitecturalProposalDialog(
    onAccept: () -> Unit,
    onReject: () -> Unit
) {
    Dialog(onDismissRequest = onReject) {
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
                // Architectural Proposal Icon
                ArchitecturalProposalIcon()

                Spacer(modifier = Modifier.height(24.dp))

                // "Review Architectural Proposal" text
                Text(
                    text = "Review Architectural Proposal",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Description text
                Text(
                    text = "Please review the submitted architectural proposal. Accept to proceed with the project or reject to request changes.",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Accept button
                Button(
                    onClick = onAccept,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00695C)
                    )
                ) {
                    Text(
                        text = "Accept Proposal",
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Reject button
                OutlinedButton(
                    onClick = onReject,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF00695C)
                    )
                ) {
                    Text(
                        text = "Reject Proposal",
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@Composable
fun ArchitecturalProposalIcon() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(
                color = Color(0x1500695C),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        // Architectural Proposal icon with outer circle
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFF00695C)),
            contentAlignment = Alignment.Center
        ) {
            // Architectural Proposal symbol (using an emoji for simplicity)
            Text(
                text = "📜",
                fontSize = 36.sp,
                color = Color.White
            )
        }

        // Small decorative elements
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(Color(0xFF00695C), CircleShape)
                .align(Alignment.TopEnd)
        )

        Box(
            modifier = Modifier
                .size(16.dp)
                .background(Color(0xFF00695C), CircleShape)
                .align(Alignment.BottomStart)
        )

        // Proposal indicators
        Text(
            text = "✔",  // Checkmark symbol for acceptance
            fontSize = 18.sp,
            color = Color(0xFF00695C),
            modifier = Modifier.align(Alignment.TopStart)
        )

        Text(
            text = "✘",  // Cross symbol for rejection
            fontSize = 18.sp,
            color = Color(0xFF00695C),
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ArchitecturalProposalDialogPreview() {
    MaterialTheme {
        Surface(color = Color.White) {
            ArchitecturalProposalDialog(
                onAccept = {},
                onReject = {}
            )
        }
    }
}