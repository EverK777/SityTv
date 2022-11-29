package com.jobsity.sitytv.core.helpers

import kotlinx.coroutines.CoroutineDispatcher

interface SafeApiRequest {
    suspend fun <T> safeApiRequest(dispatcher: CoroutineDispatcher, apiRequest: suspend () -> T): ApiResultHandle<T>
}
