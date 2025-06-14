package com.xash.sprout.app.core.manager

import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedNavigationHost(navController: NavHostController) {
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination?.route ?: "home"

    Crossfade(targetState = currentDestination, label = "screenTransition") { screen ->
        when (screen) {
            "home" -> HomeScreen(onNavigate = { navController.navigate("details") })
            "details" -> DetailsScreen(onBack = { navController.popBackStack() })
        }
    }

    // Dummy NavHost to register the routes (empty bodies)
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {}
        composable("details") {}
    }
}


// demo pages !

@Composable
fun DetailsScreen(onBack: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = onBack) {
            Text("Back to Home")
        }
    }
}

@Composable
fun HomeScreen(onNavigate: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize().background(Color.Blue.copy(0.2f)), contentAlignment = Alignment.Center) {
        Button(onClick = onNavigate) {
            Text("Go to Details")
        }
    }
}