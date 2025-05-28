package com.example.kidlearn

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.rememberNavController
import com.example.kidlearn.core.theme.KidLearnTheme
import com.example.kidlearn.presentation.navigation.AppNavGraph

//import dagger.hilt.android.AndroidEntryPoint
//import javax.inject.Inject

//@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
//    @Inject
//    lateinit var greeting: String
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KidLearnTheme {
                val navController = rememberNavController()
                AppNavGraph(navController = navController)
            }
        }
    }
}