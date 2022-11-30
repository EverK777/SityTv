package com.jobsity.sitytv.presentation.features.home.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.jobsity.sitytv.presentation.composables.PagerShowsList
import com.jobsity.sitytv.presentation.features.home.HomeViewModelEvents

@ExperimentalComposeUiApi
@ExperimentalPagerApi
@Composable
fun ShowsComposable(
    homeViewModelEvents: HomeViewModelEvents
) {
    val lazyListState = rememberLazyListState()
    val shows = homeViewModelEvents.getShows.collectAsLazyPagingItems()
    val visible = shows.itemCount > 0

    val pagerState = PagerState(
        pageCount = shows.itemCount,
        currentPage = if (shows.itemCount > 0) homeViewModelEvents.currentShowIndex.value else 0,
        offscreenLimit = 3
    )

    ConstraintLayout {
        val (content, progress) = createRefs()
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .constrainAs(content) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            state = lazyListState
        ) {
            item {
                PagerShowsList(viewModel = homeViewModelEvents, pagerState = pagerState, shows = shows)
            }
        }

        CircularProgressIndicator(
            modifier = Modifier
                .alpha(if (visible) 0f else 1f)
                .constrainAs(progress) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}
