package com.jobsity.sitytv.presentation.composables.expandable_view

import kotlinx.coroutines.flow.StateFlow

interface ExpandableViewModel {
    fun onItemClicked(itemId: Int)
    val itemIds: StateFlow<List<Int>>
}
