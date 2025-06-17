package com.xash.sprout.app.experimental.coilDemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.delay

@Composable
fun ImageScreen() {
    val imageUrl = "https://images.unsplash.com/photo-1503023345310-bd7c1de61c7d"

    FullScreenImageViewer(imageUrl = imageUrl)
}

@Composable
fun FullScreenImageViewer(
    imageUrl: String,
    onBack: () -> Unit = {}
) {
    val painter = rememberAsyncImagePainter(model = imageUrl)
    val state = painter.state

    // Delay loader visibility to avoid flicker
    var showLoading by remember { mutableStateOf(false) }

    LaunchedEffect(state) {
        if (state is AsyncImagePainter.State.Loading) {
            delay(150) // optional: tweak to 100–200ms
            showLoading = true
        } else {
            showLoading = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .clickable { onBack() }
    ) {
        when (state) {
            is AsyncImagePainter.State.Error -> {
                Text(
                    text = "Image failed to load.",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                Image(
                    painter = painter,
                    contentDescription = "Fullscreen image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                )
            }
        }

        // Show loader **only if it remains loading for long enough**
        if (state is AsyncImagePainter.State.Loading && showLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color.White
            )
        }
    }
}

