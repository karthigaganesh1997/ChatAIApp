package com.example.taxbandits.data.remote

import com.example.taxbandits.domain.model.ChatRequest
import com.example.taxbandits.domain.model.ChatResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface ApiInterface {

    @Headers(
        "Content-Type: application/json",
        "Authorization: Bearer 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"
    )
    @POST("chat/completions")
    suspend fun sendMessage(@Body request: ChatRequest): Response<ChatResponse>
}