package com.jobsity.sitytv.core.helpers

sealed interface ApiResultHandle<out T> {
    data class Success<out T>(val value: T) : ApiResultHandle<T>
    data class ApiError(val code: String? = null, val errorMessage: String?) : ApiResultHandle<Nothing>
    data class NetworkError(val errorNetworkMessage: String) : ApiResultHandle<Nothing>
}
