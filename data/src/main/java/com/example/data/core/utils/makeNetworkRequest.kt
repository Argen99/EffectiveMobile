package com.example.data.core.utils

import com.example.core.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal fun <T> makeNetworkRequest(
    request: suspend () -> T
) =
    flow<Either<String, T>> {
        request().also {
            emit(Either.Success(value = it))
        }
    }.flowOn(Dispatchers.IO).catch { exception ->
        exception.printStackTrace()
        emit(Either.Error(value = exception.message ?: UNKNOWN_ERROR_MESSAGE))
    }

internal fun <T> makeRequest(
    flow: suspend () -> Flow<T>
) =
    flow<Either<String, T>> {
        flow().collect {
            emit(Either.Success(value = it))
        }
    }.flowOn(Dispatchers.IO).catch { exception ->
        exception.printStackTrace()
        emit(Either.Error(value = exception.message ?: UNKNOWN_ERROR_MESSAGE))
    }

const val UNKNOWN_ERROR_MESSAGE = "Error Occurred!"