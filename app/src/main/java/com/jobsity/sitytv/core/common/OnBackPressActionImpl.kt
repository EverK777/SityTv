package com.jobsity.sitytv.core.common

import androidx.appcompat.app.AppCompatActivity

class OnBackPressActionImpl : OnBackPressAction {

    private var activity: AppCompatActivity? = null

    override fun configActivity(activity: AppCompatActivity?) {
        this.activity = activity
    }

    override fun onBackPress() {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }
}
