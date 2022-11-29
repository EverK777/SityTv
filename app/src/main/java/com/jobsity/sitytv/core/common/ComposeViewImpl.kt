package com.jobsity.sitytv.core.common

import androidx.compose.ui.platform.ComposeView
import com.google.android.material.composethemeadapter.MdcTheme

class ComposeViewImpl : ComposeViewConfig {
    override fun initComposeView(content: () -> Unit, composeView: ComposeView) {
        composeView.setContent {
            MdcTheme {
                content.invoke()
            }
        }
    }
}
