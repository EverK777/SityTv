package com.jobsity.sitytv.core.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView

interface ComposeViewConfig {
    fun initComposeView(content: @Composable () -> Unit, composeView: ComposeView)
}
