package com.example.kidlearn.presentation.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.kidlearn.presentation.navigation.Screen

@Composable
fun SplashScreen(navController: NavHostController) {
    SplashScreenContent(
        onClick = { navController.navigate(Screen.Onboarding.route) }
    )
}

@Composable
private fun SplashScreenContent(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.kidlearn_logo),
                contentDescription = null,
                modifier = Modifier.size(120.dp)
            )
            Text(
                text = "KidLearn",
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Button(
                onClick = onClick,
                shape = CircleShape,
                modifier = Modifier
                    .size(56.dp)
                    .background(color = MaterialTheme.colorScheme.secondary)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}