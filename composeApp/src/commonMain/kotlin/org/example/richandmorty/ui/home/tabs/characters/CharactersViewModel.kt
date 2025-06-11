package org.example.richandmorty.ui.home.tabs.characters

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.example.richandmorty.domain.GetRandomCharacter
import org.example.richandmorty.domain.model.CharacterModel

class CharactersViewModel(
    val getRandomCharacter: GetRandomCharacter
) : ViewModel() {
    private val _state = MutableStateFlow<CharactersState>(CharactersState())
    val state: StateFlow<CharactersState> = _state

    init {
        viewModelScope.launch {
            val result: CharacterModel = withContext(Dispatchers.IO) {
                getRandomCharacter()
            }

            _state.update {state -> state.copy(characterOfTheDay = result) }
        }
    }
}