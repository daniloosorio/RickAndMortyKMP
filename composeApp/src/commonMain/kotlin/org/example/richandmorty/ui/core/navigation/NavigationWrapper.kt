package org.example.richandmorty.ui.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.json.Json
import org.example.richandmorty.domain.model.CharacterModel
import org.example.richandmorty.ui.core.navigation.Routes.Home
import org.example.richandmorty.ui.detail.CharacterDetailScreen
import org.example.richandmorty.ui.home.HomeScreen

@Composable
fun NavigationWrapper() {
    val mainNavController = rememberNavController()

    NavHost(navController = mainNavController, startDestination = Home.route) {
        composable(route = Home.route) {
            HomeScreen(mainNavController)
        }

        composable<CharacterDetail> { navBackStackEntry ->
            val characterDetailEncoding: CharacterDetail =
                navBackStackEntry.toRoute<CharacterDetail>()
            val characterModel: CharacterModel =
                Json.decodeFromString<CharacterModel>(characterDetailEncoding.characterModel)
            CharacterDetailScreen(
                characterModel = characterModel,
                onBackPressed = { mainNavController.popBackStack() })
        }
    }
}