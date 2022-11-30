package com.jobsity.sitytv.core.data.external.repository

import com.jobsity.sitytv.core.domain.models.ShowResponse
import com.jobsity.sitytv.core.helpers.ApiResultHandle

interface RemoteRepository {
    suspend fun requestShows(page: Int): ApiResultHandle<ShowResponse>
}
