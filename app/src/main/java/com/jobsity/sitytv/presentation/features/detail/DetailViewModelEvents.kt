package com.jobsity.sitytv.presentation.features.detail

import com.jobsity.sitytv.core.domain.models.SeasonsResponse
import com.jobsity.sitytv.core.helpers.RequestStateApi
import kotlinx.coroutines.flow.Flow

interface DetailViewModelEvents {
    fun seasonsFlow(showId: Int): Flow<RequestStateApi<SeasonsResponse>>
}
