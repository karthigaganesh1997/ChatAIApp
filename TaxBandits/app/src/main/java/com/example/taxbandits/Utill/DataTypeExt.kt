package com.example.taxbandits.Utill

fun Int?.validateInt(): Int = this ?: 0

fun Long?.validateLong(): Long = this ?: 0L

fun Double?.validateDouble(): Double = this ?: 0.0

fun Int?.isValidInt(): Boolean = validateInt() > 0

fun Boolean?.validateBoolean(): Boolean = this ?: false

fun Boolean?.notValidateBoolean(): Boolean = !validateBoolean()

fun Float?.floatToInt(): Int = this?.toInt() ?: 0

fun Int?.intToFloat(): Float = this?.toFloat() ?: 0F

fun Double?.isValidDouble(): Boolean = this != null && this > 0.0

fun Double?.doubleToString(): String = this?.toString() ?: ""

