package org.example.richandmorty.ui.core.navigation.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.richandmorty.ui.core.navigation.CharacterDetail
import org.example.richandmorty.ui.core.navigation.Routes
import org.example.richandmorty.ui.home.tabs.characters.CharactersScreens
import org.example.richandmorty.ui.home.tabs.episodes.EpisodesScreen

@Composable
fun NavigationBottomWrapper(
    navHostController: NavHostController,
    mainNavController: NavHostController
) {
    NavHost(navController=navHostController, startDestination = Routes.Episodes.route){
        composable(route = Routes.Episodes.route){
            EpisodesScreen()
        }
        composable(route = Routes.Characters.route){
            CharactersScreens(
                navigateToDetail = { characterModel ->
                    val encode: String = Json.encodeToString(characterModel)
                    mainNavController.navigate(CharacterDetail(encode))
                }
            )
        }
    }
}