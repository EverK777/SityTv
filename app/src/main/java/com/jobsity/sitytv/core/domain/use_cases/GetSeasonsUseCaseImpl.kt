package com.jobsity.sitytv.core.domain.use_cases

import com.jobsity.sitytv.core.data.external.repository.RemoteRepository
import com.jobsity.sitytv.core.domain.models.SeasonsResponse
import com.jobsity.sitytv.core.helpers.ApiResultHandle
import com.jobsity.sitytv.core.helpers.RequestStateApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSeasonsUseCaseImpl constructor(
    private val remoteRepository: RemoteRepository

) : GetSeasonsUseCase {
    override fun getSeasons(id: Int): Flow<RequestStateApi<SeasonsResponse>> = flow {
        emit(RequestStateApi.Loading)

        when (val result = remoteRepository.requestSeasons(id)) {
            is ApiResultHandle.ApiError -> emit(RequestStateApi.Error(result.code, result.errorMessage))
            is ApiResultHandle.NetworkError -> emit(RequestStateApi.Error("00", result.errorNetworkMessage))
            is ApiResultHandle.Success -> emit(RequestStateApi.Success(result.value))
        }
    }
}
