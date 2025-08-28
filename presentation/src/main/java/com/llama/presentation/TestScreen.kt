package com.llama.presentation

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.llama.presentation.test.TestViewModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.CameraPositionState
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun TestScreen(
    modifier: Modifier = Modifier,
    viewModel: TestViewModel = hiltViewModel(),
    placesClient: PlacesClient,
) {
    Box(
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
        Column {
            Button(
                onClick = {
//                viewModel.getMiddlePoint(
//                    middlePointRequest = "서울시청과 동대문",
//                    placeType = "맛집"
//                )
                    viewModel.getPlaceDetail(
                        placesClient = placesClient,
                        placeId = "ChIJm7oRy-tVZDURS9uIugCbJJE"
                    )
                }
            ) {
                Text("눌러욧")
            }

            PlacePickerScreen(
                modifier = Modifier
                    .padding(top = 12.dp)
            )
        }
    }
}

@Composable
fun PlacePickerScreen(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    // 1. ActivityResultLauncher 생성
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                if (intent != null) {
                    // 3. Place 객체 얻기
                    val place = Autocomplete.getPlaceFromIntent(intent)

                    // 4. PlaceId 가져오기
                    val placeId = place.id
                    Log.d("PlaceId", "Place ID: $placeId")
                }
            } else if (result.resultCode == Activity.RESULT_CANCELED) {
                Log.d("PlaceId", "사용자가 자동 완성을 취소했습니다.")
            }
        }
    )

    // UI 버튼
    Button(
        modifier = modifier,
        onClick = {
        // 2. Intent를 만들고 런처 실행
        val fields = listOf(Place.Field.ID, Place.Field.NAME)
        val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields)
            .build(context)
        launcher.launch(intent)
    }) {
        Text("장소 선택하기")
    }
}