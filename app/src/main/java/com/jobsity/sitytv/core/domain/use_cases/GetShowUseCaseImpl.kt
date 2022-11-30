package com.jobsity.sitytv.core.domain.use_cases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.jobsity.sitytv.core.domain.models.ShowItem
import kotlinx.coroutines.flow.Flow

class GetShowUseCaseImpl constructor(
    private val showPagingSource: PagingSource<Int, ShowItem>
) : GetShowUseCase {
    override fun getShowsPaged(pageSize: Int, jumpThreshold: Int): Flow<PagingData<ShowItem>> {
        return Pager(
            PagingConfig(pageSize = pageSize, jumpThreshold = jumpThreshold)
        ) {
            showPagingSource
        }.flow
    }
}
