package com.jobsity.sitytv.core.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteEntity(
    @PrimaryKey val id: Int,
    val url: String,
    val bannerUrl: String?,
    val imageUrl: String?,
    val name: String?,
    val summary: String?
)
