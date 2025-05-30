package com.example.kidlearn

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Scaffold
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kidlearn.core.theme.KidLearnTheme
import com.example.kidlearn.presentation.navigation.AppNavGraph
import com.example.kidlearn.presentation.navigation.BottomBar
import com.example.kidlearn.presentation.navigation.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false

        setContent {
            KidLearnTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues())
                    ) {
                        val navController = rememberNavController()
                        Scaffold(
                            contentWindowInsets = WindowInsets(0, 0, 0, 0),
                            bottomBar = {
                                val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
                                if (Screen.bottomNavItems.any { it.route == currentRoute }) {
                                    BottomBar(navController = navController)
                                }
                            }
                        ) { innerPadding ->
                            // Không dùng innerPadding
                            Box(modifier = Modifier.padding(innerPadding)) {
                                AppNavGraph(navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}
