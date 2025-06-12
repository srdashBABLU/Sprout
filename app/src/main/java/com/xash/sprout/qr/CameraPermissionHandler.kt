package com.xash.sprout.qr

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun CameraPermissionHandler(onPermissionGranted: @Composable () -> Unit) {
    var permissionGranted by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        permissionGranted = granted
    }

    LaunchedEffect(Unit) {
        launcher.launch(Manifest.permission.CAMERA)
    }

    if (permissionGranted) {
        onPermissionGranted()
    } else {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Camera permission is required to scan QR codes.",
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}
