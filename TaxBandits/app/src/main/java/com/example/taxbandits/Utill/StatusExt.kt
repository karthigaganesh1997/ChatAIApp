package com.example.taxbandits.Utill

const val SUCCESS = "Success"
const val FAILURE = "Failure"
const val DUPLICATE = "Duplicate"
const val UNAUTHORIZED = "Unauthorized"

fun String?.isSuccess() = validateString() == SUCCESS
fun String?.isFailure() = validateString() == FAILURE
fun String?.isDuplicate() = validateString() == DUPLICATE
fun String?.isUnauthorized() = validateString() == UNAUTHORIZED

//  Status codes
const val STATUS_CODE_200 = 200
const val STATUS_CODE_201 = 201
const val STATUS_CODE_400 = 400
const val STATUS_CODE_401 = 401
const val STATUS_CODE_403 = 403
const val STATUS_CODE_404 = 404
const val STATUS_CODE_429 = 429
const val STATUS_CODE_502 = 502
const val STATUS_CODE_503 = 503

fun Int?.isSuccess() = when (validateInt()) {
    STATUS_CODE_200, STATUS_CODE_201 -> true
    else -> false
}

fun Int?.isSessionExpired() = validateInt() == STATUS_CODE_401
fun Int?.isBadRequest() = validateInt() == STATUS_CODE_400
fun Int?.isAccessDenied() = validateInt() == STATUS_CODE_403
fun Int?.isPageNotFound() = validateInt() == STATUS_CODE_404
fun Int?.isTooManyAttempts() = validateInt() == STATUS_CODE_429
fun Int?.isBadGateway() = when (validateInt()) {
    STATUS_CODE_502, STATUS_CODE_503 -> true
    else -> false
}

fun Int?.isFBSendExceptions() = !isSuccess() && !isBadGateway() && !isSessionExpired() &&
        !isBadRequest() && !isAccessDenied() && !isTooManyAttempts()

fun String?.validateString(): String = if (isNullOrEmpty()) "" else trim()

fun String?.isValidateString(): Boolean = if (isNullOrEmpty()) false else true

