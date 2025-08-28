package com.llama.meetmind

import android.app.Application
import com.google.android.libraries.places.api.Places
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MeetMainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val apiKey = BuildConfig.GOOGLE_PLACE_SDK_KEY
        Places.initialize(this, apiKey)
    }
}