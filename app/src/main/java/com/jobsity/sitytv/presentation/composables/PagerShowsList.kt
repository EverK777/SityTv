package com.jobsity.sitytv.presentation.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.paging.compose.LazyPagingItems
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.jobsity.sitytv.R
import com.jobsity.sitytv.core.domain.models.ShowItem
import com.jobsity.sitytv.presentation.features.home.HomeViewModelEvents
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@Composable
fun PagerShowsList(
    viewModel: HomeViewModelEvents,
    pagerState: PagerState,
    shows: LazyPagingItems<ShowItem>
) {
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth(),
        itemSpacing = dimensionResource(id = R.dimen.margin_padding_horizontal_general),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalAlignment = Alignment.CenterVertically
    ) { page ->

        if (shows.itemCount <= 0) return@HorizontalPager
        val show = shows[page]
        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
        ItemPager(show?.image?.original ?: "", pageOffset = { pageOffset })
        viewModel.setCurrentShowIndexIndex(currentPage)
    }
}
