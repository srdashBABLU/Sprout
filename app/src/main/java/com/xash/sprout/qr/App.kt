package com.xash.sprout.qr

import androidx.compose.runtime.Composable

@Composable
fun QRScannerApp() {
    CameraPermissionHandler {
        QRScannerScreen() // Only show if permission is granted
    }
}
