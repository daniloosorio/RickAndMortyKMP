package org.example.richandmorty.di

import org.example.richandmorty.ui.home.tabs.characters.CharactersViewModel
import org.example.richandmorty.ui.home.tabs.episodes.EpisodesViewmodel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::EpisodesViewmodel)
    viewModelOf(::CharactersViewModel)
}