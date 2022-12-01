package com.jobsity.sitytv.presentation.features.home

import androidx.compose.runtime.State
import androidx.paging.PagingData
import com.jobsity.sitytv.core.domain.models.SearchResultResponse
import com.jobsity.sitytv.core.domain.models.ShowItem
import com.jobsity.sitytv.core.helpers.RequestStateApi
import kotlinx.coroutines.flow.Flow

interface HomeViewModelEvents {
    val currentShowIndex: State<Int>
    fun setCurrentShowIndexIndex(index: Int)
    val getShows: Flow<PagingData<ShowItem>>
    fun onSearch(text: String): Flow<RequestStateApi<SearchResultResponse>>
}
