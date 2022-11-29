package com.jobsity.sitytv.core.helpers

import android.content.Context
import com.google.common.truth.Truth
import com.jobsity.sitytv.R
import com.jobsity.sitytv.core.domain.models.ShowResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import kotlin.jvm.Throws

@RunWith(MockitoJUnitRunner::class)
class SafeApiRequestTest {

    @Mock
    lateinit var context: Context

    private lateinit var sut: SafeApiRequestImpl

    @Before
    fun setUp() {
        sut = SafeApiRequestImpl(context = context)
    }

    @Test
    fun whenRequestAnEndPointReturnsSuccessResponse(): Unit = runBlocking {
        val action = sut.safeApiRequest(Dispatchers.IO) { fakeRequest() }
        Truth.assertThat(action is ApiResultHandle.Success<ShowResponse>).isTrue()
    }

    @Test
    fun whenRequestAnEndPointThrowsHttpException(): Unit = runBlocking {
        val action = sut.safeApiRequest(Dispatchers.IO) { fakeRequestError() }
        Truth.assertThat(action is ApiResultHandle.ApiError).isTrue()
        Truth.assertThat((action as ApiResultHandle.ApiError).code).isEqualTo("401")
    }

    @Test
    fun whenRequestAnEndPointThrowsIoException(): Unit = runBlocking {
        Mockito.`when`(context.getString(R.string.something_went_network_connection)).thenReturn("Something went wrong, verify your internet connection")
        val action = sut.safeApiRequest(Dispatchers.IO) { fakeRequestNetworkError() }
        Truth.assertThat(action is ApiResultHandle.NetworkError).isTrue()
    }

    private fun fakeRequest(): ShowResponse {
        return ShowResponse()
    }

    @Throws(HttpException::class)
    private fun fakeRequestError(): ShowResponse {
        val json: String = "{\n" +
            "  \"status_message\": \"Error Api Connect.\",\n" +
            "  \"error_message\": \"No matching scope found.\",\n" +
            "  \"success\": false,\n" +
            "  \"status_code\": 401\n" +
            "}"
        throw HttpException(Response.error<ResponseBody>(401, json.toResponseBody("json/text".toMediaTypeOrNull())))
    }

    @Throws(IOException::class)
    private fun fakeRequestNetworkError(): ShowResponse {
        throw IOException()
    }
}
