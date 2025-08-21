package com.llama.meetmind

import android.app.Application
import com.naver.maps.map.NaverMapSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MeetMainApp : Application() {
    override fun onCreate() {
        super.onCreate()
//        NaverMapSdk.getInstance(this).client = NaverMapSdk.NcpKeyClient("kjk7ogkoul")
    }
}