package com.example.taxbandits.data.repository

import com.example.taxbandits.data.mapper.ApiEvent
import com.example.taxbandits.data.remote.ApiInterface
import com.example.taxbandits.domain.model.ChatRequest
import com.example.taxbandits.domain.model.ChatResponse
import com.example.taxbandits.domain.model.ErrorResponse
import com.example.taxbandits.domain.model.NetworkException
import com.example.taxbandits.domain.repository.ApiServicesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatApiImplementation @Inject constructor(private val api: ApiInterface) : ApiServicesRepository {
    override suspend fun getChatMessage(request: ChatRequest): ApiEvent<ChatResponse, ErrorResponse> {
        return try {
            // Making the API call
            val response = api.sendMessage(request)
            if (response.isSuccessful) {
                // Create Success instance of ApiEvent
                val reply = response.body()?.choices?.firstOrNull()?.message?.content
                ApiEvent.Success(
                    statusCode = response.code(),
                    data = response.body() ?: ChatResponse(
                        id = "Unknown error",
                        objectType = TODO(),
                        choices = emptyList(),
                        usage = TODO()
                    )
                )
            } else {
                // Create Failure instance of ApiEvent
                ApiEvent.Failure(
                    exception = NetworkException(
                        resultCode = response.code(),
                        errorMessage = response.errorBody()?.string() ?: "Unknown error"
                    ),
                    data = ErrorResponse(
                        message = response.message(),
                        status = response.raw().toString(),
                        statusCode = response.code(),
                        response = response.errorBody()
                    )
                )
            }
        } catch (e: Exception) {
            // Handle unexpected exceptions
            ApiEvent.Failure(
                exception = NetworkException(
                    errorMessage = e.message ?: "An unknown error occurred"
                ),
                data = ErrorResponse(
                    message = e.message
                )
            )
        }
    }
}
