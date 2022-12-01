package com.jobsity.sitytv.core.domain.use_cases

import com.jobsity.sitytv.core.data.external.repository.RemoteRepository
import com.jobsity.sitytv.core.domain.models.EpisodeDetailResponse
import com.jobsity.sitytv.core.helpers.ApiResultHandle
import com.jobsity.sitytv.core.helpers.RequestStateApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetEpisodeDetailUseCaseImpl constructor(
    private val remoteRepository: RemoteRepository
) : GetEpisodeDetailUseCase {
    override fun getEpisodeDetail(showId: Int, season: Int, episodeNumber: Int): Flow<RequestStateApi<EpisodeDetailResponse>> = flow {
        emit(RequestStateApi.Loading)

        when (val result = remoteRepository.requestEpisodeDetail(showId = showId, season = season, episodeNumber = episodeNumber)) {
            is ApiResultHandle.ApiError -> emit(RequestStateApi.Error(result.code, result.errorMessage))
            is ApiResultHandle.NetworkError -> emit(RequestStateApi.Error("00", result.errorNetworkMessage))
            is ApiResultHandle.Success -> emit(RequestStateApi.Success(result.value))
        }
    }
}
