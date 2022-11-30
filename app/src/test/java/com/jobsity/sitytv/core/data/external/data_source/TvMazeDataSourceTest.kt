package com.jobsity.sitytv.core.data.external.data_source

import com.google.common.truth.Truth
import com.jobsity.sitytv.core.domain.models.ShowResponse
import com.jobsity.sitytv.core.helpers.ApiResultHandle
import com.jobsity.sitytv.core.helpers.SafeApiRequest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvMazeDataSourceTest {

    private lateinit var tvMazeApi: TvMazeApi

    private lateinit var safeApiRequest: SafeApiRequest

    @ExperimentalCoroutinesApi
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var sut: TvMazeDataSource

    private val showResponse = ShowResponse()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        tvMazeApi = mockk()
        safeApiRequest = mockk()
        sut = TvMazeDataSource(tvMazeApi = tvMazeApi, safeApiRequest = safeApiRequest, coroutineDispatcher = testDispatcher)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun whenRequestApiThenReturnApiResultHandleSuccess() = runTest {
        coEvery {
            safeApiRequest.safeApiRequest<ShowResponse>(testDispatcher, any())
        } returns ApiResultHandle.Success(showResponse)

        val action = sut.requestShows(1)
        Truth.assertThat(action is ApiResultHandle.Success<Any>).isTrue()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun whenRequestApiThenReturnsApiResultHandleApiError() = runTest {
        coEvery {
            safeApiRequest.safeApiRequest<ShowResponse>(testDispatcher, any())
        } returns ApiResultHandle.ApiError(code = "400", "Error")

        val action = sut.requestShows(1)
        Truth.assertThat(action is ApiResultHandle.ApiError).isTrue()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun whenRequestApiThenReturnsApiResultHandleNetworkError() = runTest {
        coEvery {
            safeApiRequest.safeApiRequest<ShowResponse>(testDispatcher, any())
        } returns ApiResultHandle.NetworkError("test")

        val action = sut.requestShows(1)
        Truth.assertThat(action is ApiResultHandle.NetworkError).isTrue()
    }
}
