package com.jobsity.sitytv.core.helpers

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import com.google.accompanist.themeadapter.material.MdcTheme

fun initComposeView(content: @Composable () -> Unit, composeView: ComposeView) { // ktlint-disable annotation
    composeView.setContent {
        MdcTheme {
            content.invoke()
        }
    }
}
