package com.llama.data.main

import android.util.Log
import com.google.ai.client.generativeai.GenerativeModel
import com.llama.data.BuildConfig
import com.llama.domain.main.GetMiddlePointUseCase
import javax.inject.Inject

class GetMiddlePointUseCaseImpl @Inject constructor() : GetMiddlePointUseCase {
    private val generativeModel = GenerativeModel(
        modelName = "gemini-2.5-flasasdasdh",
        apiKey = BuildConfig.GOOGLE_GEMINI_KEY
    )

    override suspend fun invoke(
        middlePointRequest: String,
        placeType: String
    ): Result<String> = runCatching {
        val prompt = "$middlePointRequest 지역들의 중간지점에 있는 $placeType 을 추천해줘 그리고 이름, 위도 경도, 특징만 말해줘 예를 들어서 PlaceName: 어디어디 \n lat: 36.xxxx \n lon: 121.xxxx \n 특징 : xxxxx 이런식으로 말해줘"
        val response = generativeModel.generateContent(prompt)
        response.text ?: "응답 내용 없음"
    }
}