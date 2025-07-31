package com.xash.sprout.app.experimental.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object  ApiClient {
    private const val BASE_URL = "https://neurostax.in/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // ✅ Gson
            .build()
            .create(ApiService::class.java)
    }
}

