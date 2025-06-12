package com.xash.sprout

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun MyAppSplash() {
    var showSplash by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2000) // Simulate loading
        showSplash = false
    }

    if (showSplash) {
        SplashScreenUI()
    } else {
        HomeScreen() // Replace with your main screen
    }
}

@Composable
fun SplashScreenUI() {
    val scale = remember { Animatable(0.8f) }

    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(1000, easing = FastOutSlowInEasing)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.mushroomhouse), // Your logo here
                contentDescription = "Splash Logo",
                modifier = Modifier
                    .size(140.dp)
                    .scale(scale.value)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Your App Name",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
fun HomeScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "hello world !"
        )
    }
}