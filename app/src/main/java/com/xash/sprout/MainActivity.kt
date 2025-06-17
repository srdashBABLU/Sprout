package com.xash.sprout

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.xash.sprout.app.core.manager.AnimatedNavigationHost
import com.xash.sprout.app.experimental.SpeedometerPreview
import com.xash.sprout.app.experimental.coilDemo.ImageScreen
import com.xash.sprout.app.experimental.scratch_card.ScratchCardDemo
import com.xash.sprout.app.experimental.scratch_card.ScratchCardDemo2
import com.xash.sprout.app.experimental.scratch_card.ScratchCardDemo3
import com.xash.sprout.app.experimental.scratch_card.ScratchCardDemo4
import com.xash.sprout.app.experimental.youtubePlayer.MyScreen
import com.xash.sprout.app.experimental.youtubePlayer.YouTubeEmbedScreen
import com.xash.sprout.app.experimental.youtubePlayer.YouTubeScreen
//import com.xash.sprout.app.experimental.youtubePlayer.YouTubePlayerScreen
import com.xash.sprout.ui.theme.SproutTheTreeWizardTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SproutTheTreeWizardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
//                    AnimatedNavigationHost(navController)
//                    ScratchCardDemo4()
                    //MyScreen()
                    //YouTubePlayerScreen()
                    //YouTubeEmbedScreen()
                    //YouTubeScreen()
                    //ImageScreen()
                    SpeedometerPreview()
                }
            }
        }
    }
}