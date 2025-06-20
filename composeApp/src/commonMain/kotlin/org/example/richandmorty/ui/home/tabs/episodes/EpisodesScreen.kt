package org.example.richandmorty.ui.home.tabs.episodes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun EpisodesScreen(
    episodesViewmodel: EpisodesViewmodel = koinViewModel<EpisodesViewmodel>()
) {
    //val episodesViewmodel = koinViewModel<EpisodesViewmodel>()
    Box(Modifier.fillMaxSize().background(Color.Blue))
}