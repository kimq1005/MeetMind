package com.llama.presentation.test

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.llama.presentation.BuildConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class TestViewModel @Inject constructor(

): ViewModel(){
    private val generativeModel = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = BuildConfig.GOOGLE_GEMINI_KEY
    )

    fun testSet() {
        viewModelScope.launch {
            try {
                val user1Location = "위도: 37.5665, 경도: 126.9780 (서울시청)"
                val user2Location = "위도: 37.5665, 경도: 127.0000 (동대문)"
                val placeType = "맛집"

                val prompt = "두 사람의 위치 $user1Location 과 $user2Location 의 중간 지점에 있는 $placeType 을 추천해줘. 추천 장소의 이름, 주소, 특징을 포함해서 답변해줘."
                val response = generativeModel.generateContent(prompt)
                Log.d("TAG", "테스트 성공 Success: $response")
            } catch (e: Exception) {
                Log.d("TAG", "테스트 에러 Error: $e")
            }
        }
    }
}