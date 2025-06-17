package com.xash.sprout.app.experimental.youtubePlayer

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun MyScreen() {
    YouTubeCardPlayer(videoId = "szJB-Cj6Zpg?si=5KY1m8emCgaIjAlt", title = "My Favorite Video 🎬")
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun YouTubeCardPlayer(videoId: String, title: String) {
    var isPlaying by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                AndroidView(
                    factory = { context ->
                        WebView(context).apply {
                            settings.javaScriptEnabled = true
                            settings.domStorageEnabled = true
                            loadData(
                                getYouTubeEmbedHtml(videoId, isPlaying),
                                "text/html",
                                "utf-8"
                            )
                        }
                    },
                    update = {
                        it.loadData(
                            getYouTubeEmbedHtml(videoId, isPlaying),
                            "text/html",
                            "utf-8"
                        )
                    }
                )

                // Engraved Play/Pause Button Overlay
                IconButton(
                    onClick = { isPlaying = !isPlaying },
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(64.dp)
                        .background(
                            color = Color.Black.copy(alpha = 0.4f),
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        imageVector = if (isPlaying) Icons.Filled.Menu else Icons.Default.PlayArrow,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}


fun getYouTubeEmbedHtml(videoId: String, autoPlay: Boolean): String {
    val autoplayFlag = if (autoPlay) "1" else "0"
    return """
        <html>
        <body style="margin:0">
        <iframe 
            width="100%" 
            height="100%" 
            src="https://www.youtube.com/embed/$videoId?autoplay=$autoplayFlag&controls=1"
            frameborder="0" 
            allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" 
            allowfullscreen>
        </iframe>
        </body>
        </html>
    """.trimIndent()
}


//https://youtu.be/szJB-Cj6Zpg?si=5KY1m8emCgaIjAlt