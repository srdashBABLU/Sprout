package com.xash.sprout.app.experimental.api

import retrofit2.http.GET

interface ApiService {
    @GET("api/get-data.php") // your endpoint
    suspend fun getApiData(): ApiResponse
}
