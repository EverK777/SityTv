package com.jobsity.sitytv.core.helpers

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView

fun EditText.searchImeAction(onClickSearch: () -> Unit) {
    setOnEditorActionListener(
        TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                onClickSearch.invoke()
                return@OnEditorActionListener true
            }
            false
        }
    )
}
