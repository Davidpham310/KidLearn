package com.example.kidlearn.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object Home : Screen("home")
    object LearnLetters : Screen("learn_letters")
    object LearnNumbers : Screen("learn_numbers")
    object LearnAnimals : Screen("learn_animals")
    object Quiz : Screen("quiz")
}
