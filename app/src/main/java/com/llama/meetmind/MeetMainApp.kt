package com.llama.meetmind

import android.app.Application

class MeetMainApp: Application() {
    fun onCreate() {
        super.onCreate()
        NaverMapSdk.getInstance(this).client =
            NaverMapSdk.NcpKeyClient("YOUR_NCP_KEY_ID_HERE")
    }
}