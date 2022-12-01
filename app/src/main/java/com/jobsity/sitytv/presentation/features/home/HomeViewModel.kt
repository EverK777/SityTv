package com.jobsity.sitytv.presentation.features.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jobsity.sitytv.core.common.AppConstants
import com.jobsity.sitytv.core.domain.models.SearchResultResponse
import com.jobsity.sitytv.core.domain.models.ShowItem
import com.jobsity.sitytv.core.domain.use_cases.GetShowUseCase
import com.jobsity.sitytv.core.domain.use_cases.SearchShowsUseCase
import com.jobsity.sitytv.core.helpers.RequestStateApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    showUseCase: GetShowUseCase,
    private val searchShowsUseCase: SearchShowsUseCase
) : ViewModel(), HomeViewModelEvents {

    override val getShows: Flow<PagingData<ShowItem>> = showUseCase
        .getShowsPaged(AppConstants.PAGE_SIZE, AppConstants.JUMP_THRESHOLD).cachedIn(viewModelScope)

    private val _currentShowIndex: MutableState<Int> = mutableStateOf(0)

    override val currentShowIndex: State<Int>
        get() = _currentShowIndex

    override fun setCurrentShowIndexIndex(index: Int) {
        _currentShowIndex.value = index
    }

    override fun onSearch(text: String): Flow<RequestStateApi<SearchResultResponse>> {
        return searchShowsUseCase.searchShowsUseCase(text)
    }
}
