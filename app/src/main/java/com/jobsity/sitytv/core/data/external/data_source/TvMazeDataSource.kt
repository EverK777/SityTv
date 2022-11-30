package com.jobsity.sitytv.core.data.external.data_source

import com.jobsity.sitytv.core.domain.models.ShowResponse
import com.jobsity.sitytv.core.helpers.ApiResultHandle
import com.jobsity.sitytv.core.helpers.SafeApiRequest
import kotlinx.coroutines.CoroutineDispatcher

class TvMazeDataSource constructor(
    private val tvMazeApi: TvMazeApi,
    private val safeApiRequest: SafeApiRequest,
    private val coroutineDispatcher: CoroutineDispatcher

) : RemoteDataSource {
    override suspend fun requestShows(page: Int): ApiResultHandle<ShowResponse> {
        return safeApiRequest.safeApiRequest(coroutineDispatcher) { tvMazeApi.requestCurrentSeriesList(page) }
    }
}
