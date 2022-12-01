package com.jobsity.sitytv.presentation.composables.expandable_view

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jobsity.sitytv.R
import com.jobsity.sitytv.core.domain.models.SeasonsResponse
import com.jobsity.sitytv.core.helpers.getListOfEpisodes

@Composable
fun ExpandableViewComposable(
    viewModel: ExpandableViewModel,
    seasons: SeasonsResponse,
    onEpisodeClick: (number: Int) -> Unit
) {
    val itemIds by viewModel.itemIds.collectAsState()

    Box() {
        LazyColumn {
            itemsIndexed(seasons) { index, season ->
                Box {
                    Column {
                        HeaderView(
                            seasonText = String.format(stringResource(id = R.string.season), season.number),
                            onClickItem = { viewModel.onItemClicked(index) }
                        )
                        ExpandableView(
                            episodesList = season.getListOfEpisodes(stringResource(id = R.string.episode)),
                            isExpanded = itemIds.contains(index),
                            onEpisodeClick = onEpisodeClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderView(seasonText: String, onClickItem: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable(
                onClick = onClickItem
            )
            .padding(8.dp)
    ) {
        Text(
            text = seasonText,
            fontWeight = FontWeight.W700,
            style = MaterialTheme.typography.subtitle1,
            color = colorResource(R.color.font_body_1),
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ExpandableView(episodesList: List<String>, isExpanded: Boolean, onEpisodeClick: (number: Int) -> Unit) {
    // Opening Animation
    val expandTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(300)
        ) + fadeIn(
            animationSpec = tween(300)
        )
    }

    // Closing Animation
    val collapseTransition = remember {
        shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(300)
        ) + fadeOut(
            animationSpec = tween(300)
        )
    }

    AnimatedVisibility(
        visible = isExpanded,
        enter = expandTransition,
        exit = collapseTransition
    ) {
        Box(modifier = Modifier.padding(15.dp)) {
            Column {
                episodesList.forEachIndexed { index, item ->
                    Box(
                        modifier = Modifier
                            .clickable(
                                onClick = { onEpisodeClick.invoke(index) }
                            )
                            .padding(8.dp)
                    ) {
                        Text(
                            text = item,
                            fontSize = 16.sp,
                            color = colorResource(R.color.font_body_1),
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
