package org.example.richandmorty.ui.home.tabs.characters

import org.example.richandmorty.domain.model.CharacterModel

data class CharactersState(
    val characterOfTheDay: CharacterModel? = null
)