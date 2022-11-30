package com.jobsity.sitytv.core.domain.use_cases

import androidx.paging.PagingData
import com.jobsity.sitytv.core.domain.models.ShowItem
import kotlinx.coroutines.flow.Flow

interface GetShowUseCase {
    fun getShowsPaged(pageSize: Int, jumpThreshold: Int): Flow<PagingData<ShowItem>>
}
