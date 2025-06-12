package com.xash.sprout

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.xash.sprout.ui.theme.SproutTheTreeWizardTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SproutTheTreeWizardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyAppSplash()
                }
            }
        }
    }
}