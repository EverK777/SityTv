package com.jobsity.sitytv.core.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeasonsItem(
    val id: Int,
    val image: Image?,
    val number: Int?,
    val episodeOrder: Int?
) : Parcelable
