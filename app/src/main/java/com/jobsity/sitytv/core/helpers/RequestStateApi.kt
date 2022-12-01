package com.jobsity.sitytv.core.helpers

sealed interface RequestStateApi<out T> {
    data class Success<out T>(val value: T) : RequestStateApi<T>
    data class Error(val code: String? = null, val errorMessage: String?) : RequestStateApi<Nothing>
    object Loading : RequestStateApi<Nothing>
}
