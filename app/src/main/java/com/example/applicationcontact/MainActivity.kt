package com.example.applicationcontact

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.applicationcontact.model.ObjectBox

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ObjectBox.init(this) // S'assurer que cette méthode configure correctement la base de données
        Log.d("MainActivity", "com.example.applicationcontact.model.ObjectBox initialized with: ${ObjectBox.boxStore}")
        setContent {
            val navController = rememberNavController()
            App(navController, ObjectBox.boxStore)
        }
    }
}

