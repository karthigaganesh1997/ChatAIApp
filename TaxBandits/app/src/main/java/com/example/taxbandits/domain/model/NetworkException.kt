package com.example.taxbandits.domain.model

data class NetworkException(
    val resultCode: Int? = null,
    val request: String? = null,
    val headers: String? = null,
    val errorMessage: String? = "Something went wrong!",
)
