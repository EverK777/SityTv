package com.jobsity.sitytv.core.domain.use_cases

import com.jobsity.sitytv.core.domain.models.SeasonsResponse
import com.jobsity.sitytv.core.helpers.RequestStateApi
import kotlinx.coroutines.flow.Flow

interface GetSeasonsUseCase {
    fun getSeasons(id: Int): Flow<RequestStateApi<SeasonsResponse>>
}
