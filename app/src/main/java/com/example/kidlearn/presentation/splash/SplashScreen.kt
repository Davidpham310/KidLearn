package com.example.kidlearn.presentation.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.kidlearn.presentation.navigation.Screen
import com.example.kidlearn.presentation.splash.components.SplashContent
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value
    val progress = state.progress

    LaunchedEffect(Unit) {
        viewModel.onEvent(SplashEvent.StartLoading)

        delay(10_000)

        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.Splash.route) { inclusive = true }
            launchSingleTop = true
            restoreState = true
        }
    }

    SplashContent(progress = progress)
}
