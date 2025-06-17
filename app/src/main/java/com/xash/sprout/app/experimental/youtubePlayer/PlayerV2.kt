package com.xash.sprout.app.experimental.youtubePlayer

import android.annotation.SuppressLint
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.unit.dp
import java.util.regex.Pattern

@Composable
fun YouTubeEmbedScreen() {
    var url by remember { mutableStateOf("") }
    var videoId by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        OutlinedTextField(
            value = url,
            onValueChange = { url = it },
            label = { Text("Enter YouTube URL") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { videoId = extractYouTubeId(url) },
            enabled = url.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Play Video")
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (videoId.isNotEmpty()) {
            EmbeddedYouTubePlayer(videoId)
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun EmbeddedYouTubePlayer(videoId: String) {
    val context = LocalContext.current

    val html = """
        <html>
        <body style="margin:0;">
        <iframe 
            width="100%" 
            height="100%" 
            src="https://www.youtube.com/embed/$videoId?autoplay=1&modestbranding=1&playsinline=1" 
            frameborder="0"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
            allowfullscreen>
        </iframe>
        </body>
        </html>
    """.trimIndent()

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f),
        factory = {
            WebView(context).apply {
                // Force rendering fix
                setLayerType(WebView.LAYER_TYPE_HARDWARE, null)
                setBackgroundColor(android.graphics.Color.TRANSPARENT)

                settings.apply {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    mediaPlaybackRequiresUserGesture = false
                    loadWithOverviewMode = true
                    useWideViewPort = true
                }

                webChromeClient = android.webkit.WebChromeClient()
                webViewClient = WebViewClient()

                loadDataWithBaseURL(null, html, "text/html", "utf-8", null)
            }
        }
    )
}


// Extracts video ID from various formats of YouTube URLs
fun extractYouTubeId(url: String): String {
    val regex = "(?<=v=|youtu.be/|embed/)[^&#?\n]+".toRegex()
    return regex.find(url)?.value ?: ""
}

//@Composable
//fun YouTubePlayerScreen() {
//    var url by remember { mutableStateOf("") }
//    var videoId by remember { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Top
//    ) {
//        OutlinedTextField(
//            value = url,
//            onValueChange = { url = it },
//            label = { Text("Enter YouTube URL") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//            onClick = {
//                videoId = extractYouTubeId(url)
//            },
//            enabled = url.isNotBlank(),
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Play Video")
//        }
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        if (videoId.isNotEmpty()) {
//            YouTubeWebView(videoId = videoId)
//        }
//    }
//}
//
//@SuppressLint("SetJavaScriptEnabled")
//@Composable
//fun YouTubeWebView(videoId: String) {
//    val context = LocalContext.current
//    val videoUrl = "https://www.youtube.com/embed/$videoId?autoplay=1"
//
//    AndroidView(
//        modifier = Modifier
//            .fillMaxWidth()
//            .aspectRatio(16f / 9f),
//        factory = {
//            WebView(context).apply {
//                webViewClient = WebViewClient()
//                settings.javaScriptEnabled = true
//                settings.pluginState = WebSettings.PluginState.ON
//                loadUrl(videoUrl)
//            }
//        }
//    )
//}
//
//fun extractYouTubeId(url: String): String {
//    val pattern = Pattern.compile(
//        "(?:youtube\\.com.*(?:\\?|&)v=|youtu\\.be/)([^&#]*)",
//        Pattern.CASE_INSENSITIVE
//    )
//    val matcher = pattern.matcher(url)
//    return if (matcher.find()) matcher.group(1) ?: "" else ""
//}
