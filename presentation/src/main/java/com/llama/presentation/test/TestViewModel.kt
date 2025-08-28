package com.llama.presentation.test

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.ktx.api.net.awaitFetchPlace
import com.llama.domain.main.GetMiddlePointUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class TestViewModel @Inject constructor(
    private val getMiddlePointUseCase: GetMiddlePointUseCase
): ViewModel() {


    fun getMiddlePoint(
        middlePointRequest: String,
        placeType: String
    ) {
        viewModelScope.launch {
            getMiddlePointUseCase(
                middlePointRequest = middlePointRequest,
                placeType = placeType
            ).onSuccess { text ->
                Log.d("TAG", "getMiddlePoint Success: $text")
            }.onFailure { e ->
                Log.d("TAG", "getMiddlePoint Error: $e")
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getPlaceDetail(
        placesClient: PlacesClient,
        placeId: String,
    ) {
        val placeFields = listOf(
            Place.Field.NAME,
            Place.Field.ID,
            Place.Field.LAT_LNG,
            Place.Field.ADDRESS
        )

        viewModelScope.launch {
          try {
              val response = placesClient.awaitFetchPlace(placeId, placeFields)
              val result = response.place.name
              Log.d("TAG", "getPlaceDetail Success: $result")
          } catch (e: Exception) {
              Log.d("TAG", "getPlaceDetail Error: $e")
          }
        }
    }
}