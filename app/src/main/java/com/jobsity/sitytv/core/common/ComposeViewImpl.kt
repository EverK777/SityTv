package com.jobsity.sitytv.core.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import com.google.android.material.composethemeadapter.MdcTheme

class ComposeViewImpl : ComposeViewConfig {
    override fun initComposeView(content: @Composable() () -> Unit, composeView: ComposeView) { // ktlint-disable annotation
        composeView.setContent {
            MdcTheme {
                content.invoke()
            }
        }
    }
}
