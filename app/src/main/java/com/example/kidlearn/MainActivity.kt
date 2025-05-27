package com.example.kidlearn

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
//import dagger.hilt.android.AndroidEntryPoint
//import javax.inject.Inject

//@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
//    @Inject
//    lateinit var greeting: String
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()
        
//        // Safe access to greeting
//        if (::greeting.isInitialized) {
//            println("Greeting: $greeting")
//        } else {
//            println("Greeting is not initialized")
//        }
    }
}