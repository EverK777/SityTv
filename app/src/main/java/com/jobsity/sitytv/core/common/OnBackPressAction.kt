package com.jobsity.sitytv.core.common

import androidx.appcompat.app.AppCompatActivity

interface OnBackPressAction {
    fun onBackPress()
    fun configActivity(activity: AppCompatActivity?)
}
