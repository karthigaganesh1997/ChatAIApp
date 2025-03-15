package com.example.taxbandits.data.mapper

import com.example.taxbandits.domain.model.NetworkException

sealed interface ApiEvent<out L, out R> {
    data class Success<out L>(val statusCode: Int = 200,val data: L):  ApiEvent<L, Nothing>
    data class Failure<out R>(val exception: NetworkException = NetworkException(), val data: R) :   ApiEvent<Nothing, R>
}