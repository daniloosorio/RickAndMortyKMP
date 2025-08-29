package org.example.richandmorty.ui.detail

import org.example.richandmorty.domain.model.CharacterModel
import org.example.richandmorty.domain.model.EpisodeModel

data class CharacterDetailState (
    val characterModel: CharacterModel,
    val episodes: List<EpisodeModel>? = null
)