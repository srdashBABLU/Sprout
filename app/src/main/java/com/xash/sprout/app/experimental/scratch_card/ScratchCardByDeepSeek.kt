package com.xash.sprout.app.experimental.scratch_card

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.os.Build
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.delay
import kotlin.math.sqrt

@Composable
fun ScratchCardDemo3() {
    var showScratchCard by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { showScratchCard = true }) {
            Text("Reveal Your Prize!")
        }
        Spacer(modifier = Modifier.weight(1f))
    }

    if (showScratchCard) {
        ScratchCardDialog(
            onDismiss = { showScratchCard = false },
            onRevealed = { /* Handle reveal event if needed */ }
        )
    }
}

@Composable
fun ScratchCardDialog(
    onDismiss: () -> Unit,
    onRevealed: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        val scale = remember { Animatable(0.8f) }

        LaunchedEffect(Unit) {
            scale.animateTo(
                targetValue = 1f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        }

        Box(
            modifier = Modifier
                .size(300.dp)
                .shadow(24.dp, RoundedCornerShape(16.dp))
        ) {
            // Close button (outside scratch card)
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                contentDescription = "Close",
                modifier = Modifier
                    .size(36.dp)
                    .align(Alignment.TopEnd)
                    .clickable { onDismiss() }
                    .padding(8.dp)
            )

            // Scratch Card Content
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                ScratchCardSurface(
                    modifier = Modifier.fillMaxSize(),
                    onRevealed = onRevealed
                ) {
                    // Content to be revealed
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = android.R.drawable.star_big_on),
                                contentDescription = "Prize",
                                modifier = Modifier.size(64.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "You Won!",
                                style = MaterialTheme.typography.headlineMedium
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "$50 Gift Card",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ScratchCardSurface(
    modifier: Modifier = Modifier,
    scratchColor: Color = Color.LightGray,
    eraserRadius: Dp = 24.dp,
    revealThreshold: Float = 0.4f, // 40% scratched
    onRevealed: () -> Unit,
    content: @Composable () -> Unit
) {
    var isRevealed by remember { mutableStateOf(false) }
    val path = remember { Path() }
    val points = remember { mutableListOf<Offset>() }
    val density = LocalDensity.current
    val radiusPx = with(density) { eraserRadius.toPx() }
    var totalArea by remember { mutableStateOf(0f) }
    var revealedArea by remember { mutableStateOf(0f) }

    Box(modifier = modifier) {
        // Content to be revealed
        content()

        // Scratch overlay
        if (!isRevealed) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = { touch ->
                                path.moveTo(touch.x, touch.y)
                                points.add(touch)
                            },
                            onDrag = { change, _ ->
                                val position = change.position
                                path.lineTo(position.x, position.y)
                                points.add(position)
                                change.consume()
                            },
                            onDragEnd = {
                                calculateRevealedArea(points, radiusPx, totalArea) { area ->
                                    revealedArea = area
                                    if (revealedArea >= revealThreshold) {
                                        isRevealed = true
                                        onRevealed()
                                    }
                                }
                                points.clear()
                            }
                        )
                    }
            ) {
                // Calculate total area once
                if (totalArea <= 0f) totalArea = size.width * size.height

                // Draw scratch surface
                drawRect(scratchColor)

                // Draw eraser path
                val stroke = Stroke(
                    width = radiusPx * 2,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                )

                drawIntoCanvas { canvas ->
                    val paint = android.graphics.Paint().apply {
                        color = android.graphics.Color.TRANSPARENT
                        isAntiAlias = true
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            colorFilter = BlendModeColorFilter(
                                android.graphics.Color.TRANSPARENT,
                                BlendMode.CLEAR
                            )
                        } else {
                            setXfermode(PorterDuffXfermode(PorterDuff.Mode.CLEAR))
                        }
                    }
                    canvas.nativeCanvas.drawPath(path.asAndroidPath(), paint)
                }
            }
        }
    }
}

private fun calculateRevealedArea(
    points: List<Offset>,
    radius: Float,
    totalArea: Float,
    onCalculated: (Float) -> Unit
) {
    if (points.size < 2) return

    var area = 0f
    val circleArea = Math.PI * radius * radius

    // Approximate revealed area by circle coverage
    area += (circleArea * points.size).toFloat()

    // Add paths between points
    for (i in 0 until points.size - 1) {
        val distance = distanceBetween(points[i], points[i + 1])
        area += distance * radius * 2
    }

    onCalculated(area.toFloat() / totalArea)
}

private fun distanceBetween(a: Offset, b: Offset): Float {
    return sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y))
}