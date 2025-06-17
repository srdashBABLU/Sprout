package com.xash.sprout.app.experimental.scratch_card

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.delay

@Composable
fun ScratchCardTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Color(0xFF6366F1),
            secondary = Color(0xFFEC4899),
            background = Color(0xFF0F172A),
            surface = Color(0xFF1E293B)
        ),
        content = content
    )
}

@Composable
fun ScratchCardDemo2() {
    var showScratchCard by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF1E293B),
                        Color(0xFF0F172A)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        // Demo screen content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "🎰 Scratch Card Demo",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Tap the button to reveal your scratch card!",
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Button(
                onClick = { showScratchCard = true },
                modifier = Modifier
                    .padding(16.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6366F1)
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "🎁 Show Scratch Card",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        // Scratch card overlay
        if (showScratchCard) {
            ScratchCardOverlay(
                onClose = { showScratchCard = false }
            )
        }
    }
}

@Composable
fun ScratchCardOverlay(onClose: () -> Unit) {
    val density = LocalDensity.current
    var isVisible by remember { mutableStateOf(false) }

    // Animation values
    val scale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0.3f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(300)
    )

    LaunchedEffect(Unit) {
        delay(100)
        isVisible = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.8f))
            .zIndex(10f),
        contentAlignment = Alignment.Center
    ) {
        // Close button
        IconButton(
            onClick = onClose,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(32.dp)
                .background(
                    Color.White.copy(alpha = 0.2f),
                    CircleShape
                )
                .size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }

        // Scratch card
        ScratchCard(
            modifier = Modifier
                .scale(scale)
                .alpha(alpha)
                .padding(32.dp)
        )
    }
}

@Composable
fun ScratchCard(modifier: Modifier = Modifier) {
    var scratchedPath by remember { mutableStateOf(Path()) }
    var scratchProgress by remember { mutableFloatStateOf(0f) }
    var isRevealed by remember { mutableStateOf(false) }
    var isAutoRevealing by remember { mutableStateOf(false) }

    val revealAnimation by animateFloatAsState(
        targetValue = if (isRevealed) 1f else 0f,
        animationSpec = tween(1000, easing = EaseOutCubic)
    )

    // Auto-reveal when 40% scratched
    LaunchedEffect(scratchProgress) {
        if (scratchProgress >= 0.4f && !isRevealed && !isAutoRevealing) {
            isAutoRevealing = true
            delay(500)
            isRevealed = true
        }
    }

    Card(
        modifier = modifier
            .size(width = 300.dp, height = 400.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Background content (revealed content)
            WinningContent(
                modifier = Modifier.fillMaxSize(),
                revealProgress = revealAnimation
            )

            // Scratch layer
            var lastPosition by remember { mutableStateOf(Offset.Zero) }

            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = { offset ->
                                scratchedPath.moveTo(offset.x, offset.y)
                                lastPosition = offset
                            },
                            onDrag = { _, dragAmount ->
                                if (!isRevealed) {
                                    val newPosition = lastPosition + dragAmount
                                    scratchedPath.lineTo(newPosition.x, newPosition.y)
                                    lastPosition = newPosition

                                    // Calculate scratch progress
                                    scratchProgress = calculateScratchProgress(
                                        scratchedPath,
                                        size.width.toFloat(),
                                        size.height.toFloat()
                                    )
                                }
                            }
                        )
                    }
            ) {
                if (!isRevealed || revealAnimation < 1f) {
                    drawScratchLayer(scratchedPath, revealAnimation)
                }
            }


            // Scratch instruction
            if (scratchProgress == 0f && !isRevealed) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "✨ Scratch Here ✨",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Use your finger to scratch and reveal your prize!",
                            fontSize = 12.sp,
                            color = Color.White.copy(alpha = 0.8f),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            // Progress indicator
            if (scratchProgress > 0f && !isRevealed) {
                LinearProgressIndicator(
                    progress = scratchProgress,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    color = Color(0xFF10B981),
                    trackColor = Color.White.copy(alpha = 0.3f)
                )
            }
        }
    }
}

@Composable
fun WinningContent(
    modifier: Modifier = Modifier,
    revealProgress: Float
) {
    val prizes = listOf("🎁", "💎", "🏆", "⭐", "🎊")
    val selectedPrize = remember { prizes.random() }

    Box(
        modifier = modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF6366F1),
                        Color(0xFFEC4899),
                        Color(0xFF10B981)
                    ),
                    start = Offset.Zero,
                    end = Offset.Infinite
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .alpha(0.3f + (revealProgress * 0.7f))
                .scale(0.8f + (revealProgress * 0.2f))
        ) {
            Text(
                text = "🎉 CONGRATULATIONS! 🎉",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Text(
                text = selectedPrize,
                fontSize = 80.sp,
                textAlign = TextAlign.Center
            )

            Text(
                text = "You Won!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Amazing Prize Inside",
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.9f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White.copy(alpha = 0.2f)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Claim Prize",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

fun DrawScope.drawScratchLayer(scratchedPath: Path, revealProgress: Float) {
    val scratchPaint = Paint().apply {
        isAntiAlias = true
        style = PaintingStyle.Stroke
        strokeWidth = 60f
        strokeCap = StrokeCap.Round
        strokeJoin = StrokeJoin.Round
        blendMode = BlendMode.Clear
    }

    // Draw the scratch surface
    val surfacePaint = Paint().apply {
        isAntiAlias = true
        color = Color(0xFF94A3B8)
    }

    // Create scratch surface with gradient
    drawRect(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFF94A3B8),
                Color(0xFF64748B),
                Color(0xFF475569)
            ),
            start = Offset.Zero,
            end = Offset(size.width, size.height)
        ),
        alpha = 1f - revealProgress
    )

    // Apply scratched areas
    clipPath(scratchedPath, clipOp = ClipOp.Difference) {
        drawRect(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color(0xFF94A3B8),
                    Color(0xFF64748B),
                    Color(0xFF475569)
                ),
                start = Offset.Zero,
                end = Offset(size.width, size.height)
            ),
            alpha = 1f - revealProgress
        )
    }
}

fun calculateScratchProgress(path: Path, width: Float, height: Float): Float {
    // Simplified progress calculation based on path bounds
    val pathBounds = path.getBounds()
    val scratchedArea = pathBounds.width * pathBounds.height
    val totalArea = width * height
    return (scratchedArea / totalArea).coerceIn(0f, 1f)
}

@Preview(showBackground = true)
@Composable
fun ScratchCardDemoPreview() {
    ScratchCardTheme {
        ScratchCardDemo()
    }
}