package com.xash.sprout.app.core.manager.nav

sealed class MainScreen(val route: String) {
    object Home : MainScreen("home")
    object Profile : MainScreen("profile")
}
