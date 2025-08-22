package org.example.richandmorty.ui.detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.example.richandmorty.domain.model.CharacterModel

class CharacterDetailViewModel(characterModel: CharacterModel) : ViewModel()  {
    private val _uiState = MutableStateFlow(CharacterDetailState(characterModel))
    val uiState : StateFlow<CharacterDetailState> = _uiState
}