package org.example.richandmorty.ui.home.tabs.episodes

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import org.example.richandmorty.domain.model.CharacterModel
import org.example.richandmorty.domain.model.EpisodeModel

data class EpisodesState (
    val characters: Flow<PagingData<EpisodeModel>> = emptyFlow(),
    val playVideo: String = ""
)