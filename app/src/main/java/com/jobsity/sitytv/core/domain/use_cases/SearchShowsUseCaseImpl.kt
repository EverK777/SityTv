package com.jobsity.sitytv.core.domain.use_cases

import com.jobsity.sitytv.core.data.external.repository.RemoteRepository
import com.jobsity.sitytv.core.domain.models.SearchResultResponse
import com.jobsity.sitytv.core.helpers.ApiResultHandle
import com.jobsity.sitytv.core.helpers.RequestStateApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchShowsUseCaseImpl constructor(
    private val remoteRepository: RemoteRepository
) : SearchShowsUseCase {
    override fun searchShowsUseCase(text: String): Flow<RequestStateApi<SearchResultResponse>> = flow {
        emit(RequestStateApi.Loading)

        when (val result = remoteRepository.searchShows(text)) {
            is ApiResultHandle.ApiError -> emit(RequestStateApi.Error(result.code, result.errorMessage))
            is ApiResultHandle.NetworkError -> emit(RequestStateApi.Error("00", result.errorNetworkMessage))
            is ApiResultHandle.Success -> emit(RequestStateApi.Success(result.value))
        }
    }
}
