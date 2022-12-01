package com.jobsity.sitytv.core.data.external.repository

import com.jobsity.sitytv.core.data.external.data_source.RemoteDataSource
import com.jobsity.sitytv.core.domain.models.SeasonsResponse
import com.jobsity.sitytv.core.domain.models.ShowResponse
import com.jobsity.sitytv.core.helpers.ApiResultHandle

class TvMazeRepository constructor(
    private val remoteDataSource: RemoteDataSource
) : RemoteRepository {

    override suspend fun requestShows(page: Int): ApiResultHandle<ShowResponse> {
        return remoteDataSource.requestShows(page)
    }

    override suspend fun requestSeasons(id: Int): ApiResultHandle<SeasonsResponse> {
        return remoteDataSource.requestSeasons(id)
    }
}
