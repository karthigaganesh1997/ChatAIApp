package com.example.taxbandits.presentation.state

import com.example.taxbandits.Utill.isValidateString
import com.example.taxbandits.Utill.validateString
import com.example.taxbandits.domain.model.ChatRequest
import com.example.taxbandits.domain.model.Message

data class ChatMessageState (
    val text: String? = null,
    val isUser: Boolean
){
    fun isValidationPassed(): Boolean = text.isValidateString()


    fun requestMessage(): ChatRequest = ChatRequest(
        model = "llama3-8b-8192",
        messages = listOf(Message(role = "user", content = text.validateString()))


    )

}