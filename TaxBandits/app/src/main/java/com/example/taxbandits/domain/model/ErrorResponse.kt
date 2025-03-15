package com.example.taxbandits.domain.model

import com.example.taxbandits.Utill.isBadRequest

data class ErrorResponse(
    val message: String? = null,
    val status: String? = null,
    val statusCode: Int? = null,
    val response: Any? = null,
) {

    fun isBadRequest() = statusCode.isBadRequest()

}