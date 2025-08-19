package com.llama.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.NaverMapSdk
import com.naver.maps.map.compose.CameraPositionState
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState

@OptIn(ExperimentalNaverMapApi::class)
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Log.d("TAG", "naver: 아니 아")
            NaverMapSdk.OnAuthFailedListener {
                val wow = it.errorCode
                Log.d("TAG", "naver 인증 실패: $wow")
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Color.White
                    ),
            ) {
                val seoul = LatLng(37.532600, 127.024612)
                val cameraPositionState: CameraPositionState = rememberCameraPositionState {
                    // 카메라 초기 위치를 설정합니다.
                    position = CameraPosition(seoul, 11.0)
                }

                NaverMap(
                    modifier = Modifier
                        .fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                )
            }
        }
    }
}