package com.example.taxbandits.domain.repository

import com.example.taxbandits.data.mapper.ApiEvent
import com.example.taxbandits.domain.model.ChatRequest
import com.example.taxbandits.domain.model.ChatResponse
import com.example.taxbandits.domain.model.ErrorResponse

interface ApiServicesRepository {

   suspend fun getChatMessage(request: ChatRequest): ApiEvent<ChatResponse, ErrorResponse>
}