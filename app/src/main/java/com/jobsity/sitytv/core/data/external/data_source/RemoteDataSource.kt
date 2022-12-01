package com.jobsity.sitytv.core.data.external.data_source

import com.jobsity.sitytv.core.domain.models.SeasonsResponse
import com.jobsity.sitytv.core.domain.models.ShowResponse
import com.jobsity.sitytv.core.helpers.ApiResultHandle

interface RemoteDataSource {
    suspend fun requestShows(page: Int): ApiResultHandle<ShowResponse>
    suspend fun requestSeasons(id: Int): ApiResultHandle<SeasonsResponse>
}
