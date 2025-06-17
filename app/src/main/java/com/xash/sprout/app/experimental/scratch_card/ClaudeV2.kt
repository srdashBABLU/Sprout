package com.xash.sprout.app.experimental.scratch_card

import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.delay
import kotlin.math.pow
import kotlin.math.sqrt

@Composable
fun ScratchCardTheme2(content: @Composable () -> Unit) {
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
fun ScratchCardDemo4() {
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
            ScratchCardOverlay2(
                onClose = { showScratchCard = false }
            )
        }
    }
}

@Composable
fun ScratchCardOverlay2(onClose: () -> Unit) {
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
        ScratchCard2(
            modifier = Modifier
                .scale(scale)
                .alpha(alpha)
                .padding(32.dp)
        )
    }
}

@Composable
fun ScratchCard2(modifier: Modifier = Modifier) {
    var scratchedPoints by remember { mutableStateOf(mutableListOf<Offset>()) }
    var scratchProgress by remember { mutableFloatStateOf(0f) }
    var isRevealed by remember { mutableStateOf(false) }
    var isAutoRevealing by remember { mutableStateOf(false) }
    var cardSize by remember { mutableStateOf(androidx.compose.ui.geometry.Size.Zero) }

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
            WinningContent2(
                modifier = Modifier.fillMaxSize(),
                revealProgress = 1f // Always show background for proper scratch effect
            )

            // Scratch layer with proper masking
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = { offset ->
                                if (!isRevealed) {
                                    scratchedPoints.add(offset)
                                }
                            },
                            onDrag = { change, _ ->
                                if (!isRevealed) {
                                    scratchedPoints.add(change.position)

                                    // Calculate scratch progress
                                    scratchProgress = calculateScratchProgress(
                                        scratchedPoints,
                                        cardSize.width,
                                        cardSize.height
                                    )
                                }
                            }
                        )
                    }
            ) {
                cardSize = size
                drawScratchLayer(scratchedPoints, revealAnimation, size)
            }

            // Scratch instruction overlay
            if (scratchProgress == 0f && !isRevealed) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.3f))
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
fun WinningContent2(
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

fun DrawScope.drawScratchLayer(scratchedPoints: List<Offset>, revealProgress: Float, canvasSize: androidx.compose.ui.geometry.Size) {
    // Only draw the scratch layer if not fully revealed
    if (revealProgress < 1f) {
        // Create the scratch surface with a realistic metallic look
        drawRect(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color(0xFFC0C0C0), // Silver
                    Color(0xFF808080), // Gray
                    Color(0xFF696969), // Dim Gray
                    Color(0xFF808080), // Gray
                    Color(0xFFC0C0C0)  // Silver
                ),
                start = Offset.Zero,
                end = Offset(canvasSize.width, canvasSize.height)
            ),
            alpha = 1f - revealProgress
        )

        // Add some texture to make it look more realistic
        drawRect(
            brush = Brush.radialGradient(
                colors = listOf(
                    Color.Transparent,
                    Color.White.copy(alpha = 0.1f),
                    Color.Transparent
                ),
                center = Offset(canvasSize.width * 0.3f, canvasSize.height * 0.3f),
                radius = canvasSize.width * 0.4f
            ),
            alpha = 1f - revealProgress
        )

        // Draw scratched areas as circles
        for (point in scratchedPoints) {
            drawCircle(
                color = Color.Transparent,
                radius = 30f,
                center = point,
                blendMode = BlendMode.Clear
            )
        }

        // Add some shine effect on the scratch surface
        if (scratchedPoints.isEmpty()) {
            drawRect(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.White.copy(alpha = 0.3f),
                        Color.Transparent
                    ),
                    start = Offset.Zero,
                    end = Offset(canvasSize.width * 0.5f, canvasSize.height * 0.5f)
                ),
                alpha = 1f - revealProgress
            )
        }
    }
}

fun calculateScratchProgress(scratchedPoints: List<Offset>, width: Float, height: Float): Float {
    if (scratchedPoints.isEmpty() || width == 0f || height == 0f) return 0f

    // Create a grid to check coverage
    val gridSize = 20
    val cellWidth = width / gridSize
    val cellHeight = height / gridSize
    val scratchRadius = 30f

    var scratchedCells = 0
    val totalCells = gridSize * gridSize

    // Check each grid cell
    for (x in 0 until gridSize) {
        for (y in 0 until gridSize) {
            val cellCenterX = (x + 0.5f) * cellWidth
            val cellCenterY = (y + 0.5f) * cellHeight
            val cellCenter = Offset(cellCenterX, cellCenterY)

            // Check if any scratched point covers this cell
            val isCellScratched = scratchedPoints.any { point ->
                val distance = sqrt(
                    (point.x - cellCenter.x).pow(2) +
                            (point.y - cellCenter.y).pow(2)
                )
                distance <= scratchRadius
            }

            if (isCellScratched) {
                scratchedCells++
            }
        }
    }

    return (scratchedCells.toFloat() / totalCells.toFloat()).coerceIn(0f, 1f)
}

@Preview(showBackground = true)
@Composable
fun ScratchCardDemoPreview2() {
    ScratchCardTheme {
        ScratchCardDemo()
    }
}