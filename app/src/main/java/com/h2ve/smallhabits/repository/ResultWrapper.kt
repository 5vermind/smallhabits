package com.h2ve.smallhabits.repository

import com.h2ve.smallhabits.model.ErrorResponse

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class GenericError(val error: ErrorResponse? = null): ResultWrapper<Nothing>()
    object NetworkError: ResultWrapper<Nothing>()
}