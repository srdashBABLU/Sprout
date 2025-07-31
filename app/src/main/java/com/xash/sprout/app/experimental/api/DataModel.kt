package com.xash.sprout.app.experimental.api

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val status: String,
    val message: String,
    val developer: String,
    val year: Int,
    val features: List<String>
)
