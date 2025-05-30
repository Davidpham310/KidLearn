package com.example.kidlearn.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

@OptIn(ExperimentalAnimationApi::class)
object NavTransitions {

    private val animationDuration = 300

    val slideInRight: EnterTransition
        get() = slideInHorizontally(
            initialOffsetX = { fullWidth -> fullWidth },
            animationSpec = tween(animationDuration)
        ) + fadeIn(animationSpec = tween(animationDuration))

    val slideOutLeft: ExitTransition
        get() = slideOutHorizontally(
            targetOffsetX = { fullWidth -> -fullWidth },
            animationSpec = tween(animationDuration)
        ) + fadeOut(animationSpec = tween(animationDuration))

    val slideInLeft: EnterTransition
        get() = slideInHorizontally(
            initialOffsetX = { fullWidth -> -fullWidth },
            animationSpec = tween(animationDuration)
        ) + fadeIn(animationSpec = tween(animationDuration))

    val slideOutRight: ExitTransition
        get() = slideOutHorizontally(
            targetOffsetX = { fullWidth -> fullWidth },
            animationSpec = tween(animationDuration)
        ) + fadeOut(animationSpec = tween(animationDuration))

}
