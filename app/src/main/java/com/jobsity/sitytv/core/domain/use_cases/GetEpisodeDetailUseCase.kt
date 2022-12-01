package com.jobsity.sitytv.core.domain.use_cases

import com.jobsity.sitytv.core.domain.models.EpisodeDetailResponse
import com.jobsity.sitytv.core.helpers.RequestStateApi
import kotlinx.coroutines.flow.Flow

interface GetEpisodeDetailUseCase {
    fun getEpisodeDetail(showId: Int, season: Int, episodeNumber: Int): Flow<RequestStateApi<EpisodeDetailResponse>>
}
