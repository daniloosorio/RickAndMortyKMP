package org.example.richandmorty.ui.home.tabs.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.example.richandmorty.domain.model.CharacterModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import androidx.compose.runtime.getValue

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CharactersScreens(
    charactersViewModel: CharactersViewModel = koinViewModel<CharactersViewModel>()
){
    val uiState by charactersViewModel.state.collectAsState()

    Column(Modifier.fillMaxSize()) {
        uiState.characterOfTheDay?.let {
            Text(it.image)
        }
    }
}