package com.xash.sprout.app.ui.splash

import androidx.compose.animation.core.EaseInOutSine
import androidx.compose.animation.core.EaseOutBack
import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import java.util.concurrent.TimeUnit

// ViewModel for managing splash screen state
class SplashViewModel : ViewModel() {
    private val _isAnimating = mutableStateOf(true)
    val isAnimating: State<Boolean> = _isAnimating

    private val _showContent = mutableStateOf(false)
    val showContent: State<Boolean> = _showContent

    init {
        // Start content animation after a brief delay
        kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.Main).launch {
            delay(300)
            _showContent.value = true
        }
    }

    fun onBeginClicked() {
        // Handle navigation logic here
        _isAnimating.value = false
    }
}

// Custom image cache manager for 30-day cleanup
object ImageCacheManager {
    fun createImageRequest(context: android.content.Context, imageUrl: String): ImageRequest {
        val cacheDir = File(context.cacheDir, "image_cache")

        return ImageRequest.Builder(context)
            .data(imageUrl)
            .diskCachePolicy(coil.request.CachePolicy.ENABLED)
            .memoryCachePolicy(coil.request.CachePolicy.ENABLED)
            .build()
    }

    fun cleanupOldCache(context: android.content.Context) {
        val cacheDir = File(context.cacheDir, "image_cache")
        if (cacheDir.exists()) {
            val thirtyDaysAgo = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(30)
            cacheDir.listFiles()?.forEach { file ->
                if (file.lastModified() < thirtyDaysAgo) {
                    file.delete()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashScreen(
    onBeginClick: () -> Unit = {},
    viewModel: SplashViewModel = viewModel()
) {
    val context = LocalContext.current
    val isDarkTheme = isSystemInDarkTheme()

    // Clean up old cached images on launch
    LaunchedEffect(Unit) {
        ImageCacheManager.cleanupOldCache(context)
    }

    // Animation states
    val infiniteTransition = rememberInfiniteTransition(label = "splash_animation")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale_animation"
    )

    val alpha by animateFloatAsState(
        targetValue = if (viewModel.showContent.value) 1f else 0f,
        animationSpec = tween(1000, easing = EaseOutCubic),
        label = "alpha_animation"
    )

    val slideOffset by animateIntAsState(
        targetValue = if (viewModel.showContent.value) 0 else 100,
        animationSpec = tween(800, delayMillis = 200, easing = EaseOutBack),
        label = "slide_animation"
    )

    // Theme colors
    val backgroundColor = if (isDarkTheme) Color(0xFF0D1B0F) else Color(0xFFF8FFF9)
    val primaryColor = if (isDarkTheme) Color(0xFF4CAF50) else Color(0xFF2E7D32)
    val onPrimaryColor = if (isDarkTheme) Color.Black else Color.White
    val textColor = if (isDarkTheme) Color.White else Color(0xFF1B5E20)
    val subtitleColor = if (isDarkTheme) Color(0xFFB8B8B8) else Color(0xFF666666)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        // Background Image with Coil
        AsyncImage(
            model = ImageCacheManager.createImageRequest(
                context = context,
                imageUrl = "https://images.unsplash.com/photo-1416879595882-3373a0480b5b?w=800&q=80"
            ),
            contentDescription = "Plant background",
            modifier = Modifier
                .fillMaxSize()
                .scale(scale),
            contentScale = ContentScale.Crop,
            alpha = 0.3f
        )

        // Vignette Effect
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color.Transparent,
                            backgroundColor.copy(alpha = 0.2f),
                            backgroundColor.copy(alpha = 0.5f)
                        ),
                        radius = 800f
                    )
                )
        )

        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
                .alpha(alpha)
                .offset(y = slideOffset.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(1f))

            // Main Headline
            Text(
                text = "Plant Paradise",
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                textAlign = TextAlign.Center,
                lineHeight = 48.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Subtitle
            Text(
                text = "Discover, grow, and nurture your green companions with expert guidance",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = subtitleColor,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            // Begin Button
            Button(
                onClick = {
                    viewModel.onBeginClicked()
                    onBeginClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryColor,
                    contentColor = onPrimaryColor
                ),
                shape = RoundedCornerShape(28.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 12.dp
                )
            ) {
                Text(
                    text = "Begin Your Journey",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}

// Preview for Light Theme
@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    MaterialTheme {
        SplashScreen()
    }
}

// Preview for Dark Theme
@Preview(showBackground = true)
@Composable
fun SplashScreenDarkPreview() {
    MaterialTheme(
        colorScheme = darkColorScheme()
    ) {
        SplashScreen()
    }
}