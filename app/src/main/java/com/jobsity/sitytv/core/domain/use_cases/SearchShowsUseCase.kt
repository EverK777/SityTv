package com.jobsity.sitytv.core.domain.use_cases

import com.jobsity.sitytv.core.domain.models.SearchResultResponse
import com.jobsity.sitytv.core.helpers.RequestStateApi
import kotlinx.coroutines.flow.Flow

interface SearchShowsUseCase {
    fun searchShowsUseCase(text: String): Flow<RequestStateApi<SearchResultResponse>>
}
