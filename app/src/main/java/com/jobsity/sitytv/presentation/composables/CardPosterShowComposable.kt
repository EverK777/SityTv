package com.jobsity.sitytv.presentation.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.layout.lerp
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.jobsity.sitytv.R

@ExperimentalMaterialApi
@Composable
fun ItemPager(
    imageUrl: String,
    pageOffset: () -> Float,
    onClick: () -> Unit
) {
    Card(
        backgroundColor = MaterialTheme.colors.surface,
        elevation = dimensionResource(id = R.dimen.normal_elevation),
        onClick = onClick,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_radius_2)),
        modifier = Modifier
            .padding(vertical = dimensionResource(id = R.dimen.margin_padding_vertical_general))
            .size(
                height = dimensionResource(id = R.dimen.height_poster),
                width = dimensionResource(id = R.dimen.width_poster)
            )
            .graphicsLayer {
                val fraction = 1f - pageOffset()
                lerp(
                    start = ScaleFactor(0.85f, 0.85f),
                    stop = ScaleFactor(1f, 1f),
                    fraction = fraction
                )
                    .also {
                        scaleX = it.scaleX
                        scaleY = it.scaleY
                    }

                lerp(
                    start = 0.5.dp,
                    stop = 1.dp,
                    fraction = fraction
                ).also {
                    alpha = it.value
                }
            }
    ) {
        BannerShowComposable(imageUrl) { ContentScale.FillBounds }
    }
}
