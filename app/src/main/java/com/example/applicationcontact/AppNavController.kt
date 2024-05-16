package com.example.applicationcontact

import NavigationGraph
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import io.objectbox.BoxStore

@Composable
fun App(navController: NavHostController, boxStore: BoxStore) {
    NavigationGraph(navController = navController, boxStore = boxStore)
}
