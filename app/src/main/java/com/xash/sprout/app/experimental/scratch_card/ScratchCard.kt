package com.xash.sprout.app.experimental.scratch_card

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ScratchCardDemo() {
    var showCard by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD)),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { showCard = true }) {
            Text("Show Scratch Card")
        }

        AnimatedVisibility(
            visible = showCard,
            enter = fadeIn() + scaleIn(),
            exit = fadeOut() + scaleOut()
        ) {
            ScratchCardPopup(onClose = { showCard = false })
        }
    }
}


@Composable
fun ScratchCardPopup(onClose: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.4f))
    ) {
        // Close Button Outside
        IconButton(
            onClick = onClose,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .background(Color.White, shape = CircleShape)
        ) {
            Text("✕", fontSize = 18.sp, color = Color.Black)
        }

        // Scratch Card Box
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp)
                .background(Color.White, shape = RoundedCornerShape(24.dp))
                .padding(16.dp)
        ) {
            ScratchCard(
                modifier = Modifier.size(300.dp, 200.dp),
                overlayColor = Color.LightGray,
                thresholdToReveal = 0.4f
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFFFF59D), shape = RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "🎁 You Won ₹500!",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }
    }
}


@Composable
fun ScratchCard(
    modifier: Modifier = Modifier,
    overlayColor: Color = Color.Gray,
    thresholdToReveal: Float = 0.4f,
    content: @Composable BoxScope.() -> Unit
) {
    var revealed by remember { mutableStateOf(false) }
    val path = remember { Path() }
    val cardSize = remember { mutableStateOf(IntSize.Zero) }

    val scratchedPercent = remember { mutableFloatStateOf(0f) }

    Box(
        modifier = modifier
            .onGloballyPositioned {
                cardSize.value = it.size
            }
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    val position = change.position
                    path.addOval(
                        Rect(
                            left = position.x - 30f,
                            top = position.y - 30f,
                            right = position.x + 30f,
                            bottom = position.y + 30f
                        )
                    )
                    change.consume()
                }
            }
            .clip(RoundedCornerShape(16.dp))
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            content = content
        )

        if (!revealed) {
            Canvas(modifier = Modifier.matchParentSize()) {
                drawRect(overlayColor)
                drawPath(path = path, color = Color.Transparent, blendMode = BlendMode.Clear)

                // Check scratched percentage
                val scratchedAreaBounds = path.getBounds()
                val scratchedArea = scratchedAreaBounds.width * scratchedAreaBounds.height
                val totalArea = size.width * size.height
                scratchedPercent.floatValue = scratchedArea / totalArea

                if (scratchedPercent.floatValue >= thresholdToReveal) {
                    revealed = true
                }
            }
        }
    }
}

