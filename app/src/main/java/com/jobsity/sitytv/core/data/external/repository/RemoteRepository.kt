package com.jobsity.sitytv.core.data.external.repository

import com.jobsity.sitytv.core.domain.models.EpisodeDetailResponse
import com.jobsity.sitytv.core.domain.models.SearchResultResponse
import com.jobsity.sitytv.core.domain.models.SeasonsResponse
import com.jobsity.sitytv.core.domain.models.ShowResponse
import com.jobsity.sitytv.core.helpers.ApiResultHandle

interface RemoteRepository {
    suspend fun requestShows(page: Int): ApiResultHandle<ShowResponse>
    suspend fun requestSeasons(id: Int): ApiResultHandle<SeasonsResponse>
    suspend fun requestEpisodeDetail(showId: Int, season: Int, episodeNumber: Int): ApiResultHandle<EpisodeDetailResponse>
    suspend fun searchShows(query: String): ApiResultHandle<SearchResultResponse>
}
