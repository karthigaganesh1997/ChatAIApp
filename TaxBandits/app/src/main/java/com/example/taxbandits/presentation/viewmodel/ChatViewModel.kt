package com.example.taxbandits.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taxbandits.Utill.isSuccess
import com.example.taxbandits.data.mapper.ApiEvent
import com.example.taxbandits.domain.repository.ApiServicesRepository
import com.example.taxbandits.presentation.state.ChatMessageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(val apiServicesRepository: ApiServicesRepository) :
    ViewModel() {

    private val _state = MutableStateFlow<List<ChatMessageState>>(emptyList())
    val state: StateFlow<List<ChatMessageState>> = _state.asStateFlow()

    fun sendMessage(userMessage: String) {
        val updatedMessages = _state.value + ChatMessageState(userMessage, true)
        _state.value = updatedMessages

        viewModelScope.launch {
            try {
                // Get the last user message
                val lastUserMessage = updatedMessages.lastOrNull { it.isUser }
                if (lastUserMessage != null) {
                    // Make the API call with the last message

                    if (lastUserMessage.isValidationPassed()) {
                        val response = apiServicesRepository.getChatMessage(request = lastUserMessage.requestMessage())

                        when (response) {

                            is ApiEvent.Failure -> _state.value + ChatMessageState(
                                "Error: ${response.exception.errorMessage ?: response.data.message}",
                                isUser = false
                            )

                            is ApiEvent.Success -> {
                                try {

                                    if (response.statusCode.isSuccess()) {
                                        val reply =
                                            response.data.choices.firstOrNull()?.message?.content

                                        _state.value += ChatMessageState(
                                            reply,
                                            isUser = false
                                        )
                                    }

                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            }
                        }
                    }
                    // Update the list with the bot's response

                }
            } catch (e: Exception) {
                // Handle exceptions and update state with the error message
                _state.value += ChatMessageState("Error: ${e.message}", isUser = false)
            }
        }


    }

}