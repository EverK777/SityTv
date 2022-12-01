package com.jobsity.sitytv.presentation.features.detail

import androidx.lifecycle.ViewModel
import com.jobsity.sitytv.core.domain.models.SeasonsResponse
import com.jobsity.sitytv.core.domain.use_cases.GetSeasonsUseCase
import com.jobsity.sitytv.core.helpers.RequestStateApi
import com.jobsity.sitytv.presentation.composables.expandable_view.ExpandableViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getSeasonsUseCase: GetSeasonsUseCase
) : ViewModel(), DetailViewModelEvents, ExpandableViewModel {
    override fun seasonsFlow(showId: Int): Flow<RequestStateApi<SeasonsResponse>> {
        return getSeasonsUseCase.getSeasons(showId)
    }

    private val itemIdsList = MutableStateFlow(listOf<Int>())
    override val itemIds: StateFlow<List<Int>>
        get() = itemIdsList

    override fun onItemClicked(itemId: Int) {
        itemIdsList.value = itemIdsList.value.toMutableList().also { list ->
            if (list.contains(itemId)) {
                list.remove(itemId)
            } else {
                list.add(itemId)
            }
        }
    }
}
