package com.jobsity.sitytv.presentation.composables

import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.material.composethemeadapter.MdcTheme
import com.jobsity.sitytv.R

@Composable
fun LogoAppComposable() {
    Text(
        style = MaterialTheme.typography.h4,
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = MaterialTheme.colors.primary)) {
                append(stringResource(R.string.sity))
            }
            withStyle(SpanStyle(color = MaterialTheme.colors.onBackground)) {
                append(stringResource(R.string.tv))
            }
        }
    )
}

@Preview
@Composable
fun LogoPreview() {
    MdcTheme {
        LogoAppComposable()
    }
}
