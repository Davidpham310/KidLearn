package com.example.kidlearn.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kidlearn.presentation.home.HomeScreen
import com.example.kidlearn.presentation.learn_animals.LearnAnimalsScreen
import com.example.kidlearn.presentation.learn_letters.LearnLettersScreen
import com.example.kidlearn.presentation.learn_numbers.LearnNumbersScreen
import com.example.kidlearn.presentation.onboarding.OnboardingScreen
import com.example.kidlearn.presentation.quiz.QuizScreen
import com.example.kidlearn.presentation.splash.SplashScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) { SplashScreen(navController) }
        composable(Screen.Onboarding.route) { OnboardingScreen(navController) }
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.LearnLetters.route) { LearnLettersScreen(navController) }
        composable(Screen.LearnNumbers.route) { LearnNumbersScreen(navController) }
        composable(Screen.LearnAnimals.route) { LearnAnimalsScreen(navController) }
        composable(Screen.Quiz.route) { QuizScreen(navController) }
    }
}
