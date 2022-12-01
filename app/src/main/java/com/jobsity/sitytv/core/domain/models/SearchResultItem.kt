package com.jobsity.sitytv.core.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchResultItem(
    val score: Double?,
    val show: ShowItem?
) : Parcelable
