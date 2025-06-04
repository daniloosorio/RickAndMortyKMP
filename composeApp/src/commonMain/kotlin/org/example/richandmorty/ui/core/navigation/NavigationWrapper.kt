package org.example.richandmorty.ui.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.richandmorty.ui.core.navigation.Routes.Home
import org.example.richandmorty.ui.home.HomeScreen

@Composable
fun NavigationWrapper(){
    val mainNavController = rememberNavController()

    NavHost(navController = mainNavController,startDestination = Home.route){
        composable(route= Home.route){
            HomeScreen()
        }
    }
}