package com.jobsity.sitytv.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import com.jobsity.sitytv.R
import com.jobsity.sitytv.core.domain.models.SeasonsResponse

@ExperimentalMaterialApi
@Composable
fun SeasonsListComposable(
    isLoading: Boolean,
    data: SeasonsResponse = SeasonsResponse()
) {
    val lazyListState = rememberLazyListState()

    ConstraintLayout {
        val (content, progress) = createRefs()
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .constrainAs(content) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            state = lazyListState
        ) {
            items(data) { season ->
                Box {
                    PictureDetailComposable(imageUrl = season.image?.medium ?: "")
                    Text(
                        text = String.format(stringResource(id = R.string.season), season.number),
                        modifier = Modifier
                            .align(alignment = Alignment.BottomStart)
                            .padding(dimensionResource(id = R.dimen.margin_padding_horizontal_general)),
                        style = MaterialTheme.typography.subtitle1,
                        color = colorResource(id = R.color.font_body_1)
                    )
                }
            }
        }

        CircularProgressIndicator(
            modifier = Modifier
                .alpha(if (isLoading) 0f else 1f)
                .constrainAs(progress) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}
