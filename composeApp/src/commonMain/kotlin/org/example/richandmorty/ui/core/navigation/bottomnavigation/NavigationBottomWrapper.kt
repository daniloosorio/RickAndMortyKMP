package org.example.richandmorty.ui.core.navigation.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.example.richandmorty.ui.core.navigation.Routes
import org.example.richandmorty.ui.home.tabs.characters.CharactersScreens
import org.example.richandmorty.ui.home.tabs.episodes.EpisodesScreen

@Composable
fun NavigationBottomWrapper(navHostController: NavHostController) {
    NavHost(navController=navHostController, startDestination = Routes.Episodes.route){
        composable(route = Routes.Episodes.route){
            EpisodesScreen()
        }
        composable(route = Routes.Characters.route){
            CharactersScreens()
        }
    }
}