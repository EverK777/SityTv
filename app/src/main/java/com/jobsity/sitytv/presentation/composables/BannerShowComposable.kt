package com.jobsity.sitytv.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jobsity.sitytv.R
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun BannerShowComposable(
    imageUrl: String,
    contentScale: () -> ContentScale
) {
    GlideImage(
        imageModel = imageUrl,
        contentScale = contentScale(),
        shimmerParams = ShimmerParams(
            baseColor = MaterialTheme.colors.background,
            highlightColor = Color.LightGray,
            durationMillis = 2000,
            dropOff = 0.65f,
            tilt = 20f
        ),
        failure = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(start = 20.dp)
            ) {
                Image(
                    painterResource(R.drawable.ic_baseline_search_24),
                    contentDescription = "Error loading",
                    Modifier.size(height = 160.dp, width = 100.dp)
                )
            }
        }
    )
}
