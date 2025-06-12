package com.xash.sprout.qr

import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat

@Composable
fun QRScannerScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        CameraPreview() // Your camera integration here
        QRScannerOverlay()
    }
}

@Composable
fun CameraPreview() {
    val context = LocalContext.current
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { ctx ->
            val previewView = PreviewView(ctx).apply {
                scaleType = PreviewView.ScaleType.FILL_CENTER
            }

            val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)
            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder().build().also {
                    it.surfaceProvider = previewView.surfaceProvider
                }

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }, ContextCompat.getMainExecutor(ctx))

            previewView
        }
    )
}


@Composable
fun QRScannerOverlay() {
    val infiniteTransition = rememberInfiniteTransition()
    val lineOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val scannerSize = 280.dp

    Box(modifier = Modifier.fillMaxSize()) {
        // Dimmed Overlay with transparent center
        Canvas(modifier = Modifier.fillMaxSize()) {
            val sizePx = scannerSize.toPx()
            val left = (size.width - sizePx) / 2f
            val top = (size.height - sizePx) / 2f

            drawRect(
                color = Color(0x88000000)
            )

            drawRect(
                color = Color.Transparent,
                topLeft = Offset(left, top),
                size = Size(sizePx, sizePx),
                blendMode = BlendMode.Clear
            )
        }

        // Scanning line animation
        Box(
            modifier = Modifier
                .size(scannerSize)
                .align(Alignment.Center)
        ) {
            Canvas(
                modifier = Modifier.fillMaxSize()
            ) {
                val lineY = size.height * lineOffset
                drawLine(
                    color = Color.Cyan,
                    start = Offset(0f, lineY),
                    end = Offset(size.width, lineY),
                    strokeWidth = 4f
                )
            }
        }

        // Text or Instructions
        Text(
            text = "Align QR code inside the frame",
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp)
        )
    }
}
