package com.example.taxbandits.domain.model

data class ChatRequest(  val model: String,
                           val messages: List<Message>,
                           val max_tokens: Int = 150,
                           val temperature: Double = 0.7
)

