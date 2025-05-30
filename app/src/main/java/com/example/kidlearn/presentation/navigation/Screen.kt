package com.example.kidlearn.presentation.navigation

import androidx.annotation.DrawableRes
import com.example.kidlearn.core.components.icons.AppIcons

sealed class Screen(
    val route: String,
    @DrawableRes val icon: Int? = null,
    val label: String? = null
) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object Home : Screen("home", AppIcons.HomeIcon, "Trang chủ")
    object LearnLetters : Screen("learn_letters", AppIcons.LetterIcon, "Chữ cái")
    object LearnNumbers : Screen("learn_numbers", AppIcons.NumberIcon, "Số đếm")
    object LearnAnimals : Screen("learn_animals", AppIcons.ElephantIcon, "Động vật")
    object Quiz : Screen("quiz", AppIcons.QuizIcon, "Câu đố")

    companion object {
        val bottomNavItems = listOf(Home, Quiz, LearnLetters, LearnNumbers, LearnAnimals)
    }
}
