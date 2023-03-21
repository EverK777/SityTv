package com.jobsity.sitytv.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.jobsity.sitytv.R
import com.jobsity.sitytv.core.domain.models.SearchResultResponse
import com.jobsity.sitytv.core.domain.models.ShowItem

@ExperimentalMaterialApi
@Composable
fun SearchShowsList(
    data: SearchResultResponse = SearchResultResponse(),
    click: (itemShow: ShowItem) -> Unit
) {
    val lazyListState = rememberLazyListState()
    val interactionSource = remember { MutableInteractionSource() }

    LazyColumn(
        state = lazyListState
    ) {
        items(data) { resultSearch ->
            Row(
                Modifier.padding(dimensionResource(id = R.dimen.margin_padding_horizontal_general))
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) { resultSearch.show?.let { click.invoke(it) } }
            ) {
                PictureDetailComposable(imageUrl = resultSearch.show?.image?.original ?: "")
                Text(
                    text = resultSearch.show?.name ?: "",
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.margin_padding_horizontal_general)),
                    style = MaterialTheme.typography.subtitle2,
                    color = MaterialTheme.colors.onBackground
                )
            }
        }
    }
}
