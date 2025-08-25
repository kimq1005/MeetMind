package com.llama.presentation.test

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.llama.domain.main.GetMiddlePointUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val getMiddlePointUseCase: GetMiddlePointUseCase
): ViewModel() {
    fun getMiddlePoint(
        middlePointRequest: String,
        placeType: String
    ) {
        viewModelScope.launch {
            val text = getMiddlePointUseCase(
                middlePointRequest = middlePointRequest,
                placeType = placeType
            )
            Log.d("TAG", "getMiddlePoint: $text")
        }
    }
}