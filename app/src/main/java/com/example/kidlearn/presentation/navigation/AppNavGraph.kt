package com.example.kidlearn.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kidlearn.presentation.home.HomeScreen
import com.example.kidlearn.presentation.learn_animals.LearnAnimalsScreen
import com.example.kidlearn.presentation.learn_letters.LearnLettersScreen
import com.example.kidlearn.presentation.learn_letters.LetterViewModel
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
        composable(
            route = Screen.Splash.route,
            enterTransition = { slideInFromRight() },
            exitTransition = { slideOutToLeft() },
            popEnterTransition = { slideInFromLeft() },
            popExitTransition = { slideOutToRight() }
        ) { SplashScreen(navController) }

        composable(
            route = Screen.Home.route,
            enterTransition = { slideInFromRight() },
            exitTransition = { slideOutToLeft() },
            popEnterTransition = { slideInFromLeft() },
            popExitTransition = { slideOutToRight() }
        ) { HomeScreen(navController) }

        composable(
            route = Screen.Quiz.route,
            enterTransition = { slideInFromRight() },
            exitTransition = { slideOutToLeft() },
            popEnterTransition = { slideInFromLeft() },
            popExitTransition = { slideOutToRight() }
        ) { QuizScreen(navController) }

        composable(
            route = Screen.Onboarding.route,
            enterTransition = { slideInFromRight() },
            exitTransition = { slideOutToLeft() },
            popEnterTransition = { slideInFromLeft() },
            popExitTransition = { slideOutToRight() }
        ) { OnboardingScreen(navController) }

        composable(
            route = Screen.LearnLetters.route,
            enterTransition = { slideInFromRight() },
            exitTransition = { slideOutToLeft() },
            popEnterTransition = { slideInFromLeft() },
            popExitTransition = { slideOutToRight() }
        ) {
            val viewModel: LetterViewModel = hiltViewModel()
            LearnLettersScreen(navController , viewModel , "A")
        }

        composable(
            route = Screen.LearnNumbers.route,
            enterTransition = { slideInFromRight() },
            exitTransition = { slideOutToLeft() },
            popEnterTransition = { slideInFromLeft() },
            popExitTransition = { slideOutToRight() }
        ) { LearnNumbersScreen(navController) }

        composable(
            route = Screen.LearnAnimals.route,
            enterTransition = { slideInFromRight() },
            exitTransition = { slideOutToLeft() },
            popEnterTransition = { slideInFromLeft() },
            popExitTransition = { slideOutToRight() }
        ) { LearnAnimalsScreen(navController) }

    }
}

private fun slideInFromRight(): EnterTransition {
    return NavTransitions.slideInRight
}

private fun slideOutToLeft(): ExitTransition {
    return NavTransitions.slideOutLeft
}

private fun slideInFromLeft(): EnterTransition {
    return NavTransitions.slideInLeft
}

private fun slideOutToRight(): ExitTransition {
    return NavTransitions.slideOutRight
}
