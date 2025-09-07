package com.llama.presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
) {
    val navHostController = rememberNavController()

    Surface {
        Scaffold(
            topBar = {

            },
            content = { contentPadding ->
                NavHost(
                    modifier = Modifier
                        .padding(contentPadding),
                    navController = navHostController,
                    startDestination = MainRoute.MAIN.route,
                ) {
                    composable(route = MainRoute.MAIN.route) {
                        MainScreen()
                    }

                    composable(route = MainRoute.PLACE_DETAIL.route) {

                    }
                }
            }
        )
    }
}