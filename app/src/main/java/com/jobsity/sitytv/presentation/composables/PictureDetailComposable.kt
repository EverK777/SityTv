package com.jobsity.sitytv.presentation.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import com.jobsity.sitytv.R

@ExperimentalMaterialApi
@Composable
fun PictureDetailComposable(
    imageUrl: String,
    onClick: () -> Unit = {}
) {
    Card(
        backgroundColor = MaterialTheme.colors.surface,
        elevation = dimensionResource(id = R.dimen.normal_elevation),
        onClick = onClick,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_radius_2)),
        modifier = Modifier
            .padding(vertical = dimensionResource(id = R.dimen.margin_padding_vertical_general))
            .size(
                height = dimensionResource(id = R.dimen.height_image_detail),
                width = dimensionResource(id = R.dimen.width_image_detail)
            )
    ) {
        BannerShowComposable(imageUrl) { ContentScale.FillBounds }
    }
}
