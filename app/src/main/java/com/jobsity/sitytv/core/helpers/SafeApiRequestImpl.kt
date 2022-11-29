package com.jobsity.sitytv.core.helpers

import android.content.Context
import com.jobsity.sitytv.R
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SafeApiRequestImpl @Inject constructor(
    private val context: Context
) : SafeApiRequest {
    override suspend fun <T> safeApiRequest(dispatcher: CoroutineDispatcher, apiRequest: suspend () -> T): ApiResultHandle<T> {
        return withContext(dispatcher) {
            try {
                ApiResultHandle.Success(apiRequest.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> ApiResultHandle.NetworkError(errorNetworkMessage = context.getString(R.string.something_went_network_connection))
                    is HttpException -> {
                        val code = throwable.code()
                        ApiResultHandle.ApiError(code.toString(), throwable.message())
                    }
                    else -> {
                        ApiResultHandle.ApiError(
                            code = "500",
                            errorMessage = context.getString(R.string.something_went_network_connection)
                        )
                    }
                }
            }
        }
    }
}
