package com.llama.presentation.main

import androidx.compose.ui.graphics.vector.ImageVector

enum class MainRoute(
    val route: String,
    val contentDescription: String,
) {
    MAIN(route = "Main", contentDescription = "메인페이지"),
    PLACE_DETAIL(route = "PlaceDetail", contentDescription = "장소 상세페이지")
}