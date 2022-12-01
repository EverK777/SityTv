package com.jobsity.sitytv.core.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Links(
    val self: Self?
) : Parcelable
